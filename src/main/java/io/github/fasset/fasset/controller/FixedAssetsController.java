package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.model.FixedAsset;
import io.github.fasset.fasset.service.FixedAssetService;
import io.github.fasset.fasset.service.impl.FixedAssetServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FixedAssetsController {

    private static final Logger log = LoggerFactory.getLogger(FixedAssetsController.class);

    @Autowired
    @Qualifier("fixedAssetService")
    private FixedAssetService fixedAssetService;

    @GetMapping("/add/asset")
    public String assetAdditionForm(Model model) {

        model.addAttribute("fixedAsset", new FixedAsset());

        return "forms/addAsset";
    }

    @PostMapping("/add/asset")
    public String saveFixedAssetFromForm(@ModelAttribute FixedAsset fixedAsset, RedirectAttributes redirectAttributes) {

        // TODO replace with FixedAssetResponseDto
        if (fixedAsset != null)
            fixedAssetService.saveFixedAsset(fixedAsset);
        else
            log.error("The framework has passed a null object to the controller, it could not be save");

        String message = String.format("You have successfully added asset # %s", fixedAsset.getId());

        redirectAttributes.addFlashAttribute("successAdd", message);

        return "redirect:/add/asset";
    }
}
