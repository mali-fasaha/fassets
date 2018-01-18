package io.github.fasset.fasset.controller;

import io.github.fasset.fasset.kernel.util.DataRetrievalFromControllerException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AssetListingController {

    private static final Logger log = LoggerFactory.getLogger(AssetListingController.class);

    private final FixedAssetService fixedAssetService;

    @Autowired
    public AssetListingController(@Qualifier("fixedAssetService") FixedAssetService fixedAssetService) {
        this.fixedAssetService = fixedAssetService;
    }

    @GetMapping("/assetList")
    public String showAssetListTemplate(Model model){

        return "reports/allAssets";
    }

    @GetMapping("/listing/assets/data")
    @ResponseBody
    public List<FixedAsset> fetchAllAssets(){

        List<FixedAsset> fixedAssets;

        try {
            fixedAssets = fixedAssetService.fetchAllExistingAssets();
        } catch (Throwable e) {
            String message = "Exception encountered: Could not extract assets from repo" +
                    "for the path \"/assetList\"";
            throw new DataRetrievalFromControllerException(message,e);
        }

        log.info("Returning a list of : {} assets",fixedAssets.size());

        return fixedAssets;
    }

    @GetMapping("/listing/assets/data/{id}")
    @ResponseBody
    public FixedAsset getMonthGivenId(@PathVariable("id") int id){

        FixedAsset asset = null;

        try {
            asset = fixedAssetService.fetchAssetGivenId(id);
        } catch (Throwable e) {
            String message = String.format("Exception encountered when extracting asset with id# : %s",id);

            throw new DataRetrievalFromControllerException(message,e);
        }

        log.debug("Returning : {}",asset);

        return asset;
    }
}
