/*
 * fassets - Project for light-weight tracking of fixed assets
 * Copyright © 2018 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.kernel.storage.StorageService;
import io.github.fasset.fasset.kernel.util.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Controller for file uploads workflows
 *
 * @author edwin.njeru
 * @version $Id: $Id
 */
@Controller
public class FileUploadsController {

    private final StorageService storageService;

    /**
     * <p>Constructor for FileUploadsController.</p>
     *
     * @param storageService a {@link io.github.fasset.fasset.kernel.storage.StorageService} object.
     */
    @Autowired
    public FileUploadsController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * <p>listUploadedFiles.</p>
     *
     * @param model a {@link org.springframework.ui.Model} object.
     * @return a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    @GetMapping("/files")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll()
                                                  .map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadsController.class, "serveFile", path.getFileName().toString()).build().toString())
                                                  .collect(Collectors.toList()));
        return "uploads/uploadForm";
    }

    /**
     * <p>handleFileUpload.</p>
     *
     * @param file               a {@link org.springframework.web.multipart.MultipartFile} object.
     * @param redirectAttributes a {@link org.springframework.web.servlet.mvc.support.RedirectAttributes} object.
     * @return a {@link java.lang.String} object.
     */
    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        storageService.store(file);

        redirectAttributes.addFlashAttribute("message", "Looking Good! Your Excel File Too: \nYou have successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/files";
    }

    /**
     * <p>serveFile.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return a {@link org.springframework.http.ResponseEntity} object.
     */
    @GetMapping("files/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {

        Resource file = storageService.loadAsResource(fileName);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * <p>handleStorageFileNotFound.</p>
     *
     * @param exc a {@link io.github.fasset.fasset.kernel.util.StorageFileNotFoundException} object.
     * @return a {@link org.springframework.http.ResponseEntity} object.
     */
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {

        return ResponseEntity.notFound().build();
    }
}
