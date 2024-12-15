package demo.asset.management.controller;

import demo.asset.management.dto.AssetDto;
import demo.asset.management.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AssetController {


    private AssetService assetService;
    public AssetController(AssetService assetService){
        this.assetService = assetService;
    }

    //Create New Assets REST API
    @PostMapping
    public ResponseEntity<AssetDto> createNewAssets(@RequestBody AssetDto assetDto){
        return new ResponseEntity<>(assetService.createNewAssets(assetDto), HttpStatus.CREATED);
    }
}
