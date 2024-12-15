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

    // Create New Assets REST API
    @PostMapping
    public ResponseEntity<AssetDto> createNewAssets(@RequestBody AssetDto assetDto){
        return new ResponseEntity<>(assetService.createNewAssets(assetDto), HttpStatus.CREATED);
    }

    // Get All Assets REST API
    @GetMapping
    public ResponseEntity<List<AssetDto>> getAllAssets(){
        List<AssetDto> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    // Get Asset By Id REST API
    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAssetById(@PathVariable("id")Long id){
        AssetDto assetDto = assetService.getAssetById(id);
        return ResponseEntity.ok(assetDto);
    }

}
