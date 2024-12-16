package demo.asset.management.controller;

import demo.asset.management.dto.AssetDto;
import demo.asset.management.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AssetController {


    private AssetService assetService;
    public AssetController(AssetService assetService){
        this.assetService = assetService;
    }

    // REST API - Create New Assets
    @PostMapping
    public ResponseEntity<AssetDto> createNewAssets(@RequestBody AssetDto assetDto){
        return new ResponseEntity<>(assetService.createNewAssets(assetDto), HttpStatus.CREATED);
    }

    // REST API - Get All Assets
    @GetMapping
    public ResponseEntity<List<AssetDto>> getAllAssets(){
        List<AssetDto> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    // REST API - Get Asset by ID
    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAssetById(@PathVariable("id")Long id){
        AssetDto assetDto = assetService.getAssetById(id);
        return ResponseEntity.ok(assetDto);
    }

    // REST API - Delete Asset By ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("id") Long id){
        assetService.deleteAsset(id);
        return ResponseEntity.ok("Asset is deleted successfully!");
    }

}
