package io.github.fasset.fasset;

import io.github.fasset.fasset.kernel.storage.StorageFileNotFoundException;
import io.github.fasset.fasset.kernel.storage.StorageService;
import io.github.fasset.fasset.kernel.util.ImmutableListCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadsController {

    private final StorageService storageService;

    @Autowired
    public FileUploadsController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files")
    public String listUploadedFiles(Model model) throws IOException{

        model.addAttribute("files", storageService.loadAll()
                .map(
                        path -> MvcUriComponentsBuilder
                        .fromMethodName(FileUploadsController.class,"serveFile",
                                path.getFileName().toString())
                        .build()
                        .toString()
                )
                .collect(Collectors.toList()));
        return "uploads/uploadForm";
    }

    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){

        storageService.store(file);

        redirectAttributes.addFlashAttribute("message","You have successfully uploaded "+file.getOriginalFilename()+"!");

        return "redirect:/files";
    }

    @GetMapping("files/{fileName:.+")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName){

        Resource file = storageService.loadAsResource(fileName);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; fileName=\""+file.getFilename()+"\"")
                .body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc){

        return ResponseEntity.notFound().build();
    }
}
