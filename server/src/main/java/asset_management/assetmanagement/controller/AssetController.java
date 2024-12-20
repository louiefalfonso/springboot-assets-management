package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private final AssetService assetService;
    private final AssetRepository assetRepository;

    public AssetController(AssetService assetService, AssetRepository assetRepository) {
        this.assetService = assetService;
        this.assetRepository = assetRepository;
    }

    //POST - Create New Asset REST API
    @PostMapping
    public ResponseEntity<AssetDto> createAsset (@RequestBody AssetDto assetDto) {
        AssetDto savedAsset = assetService.createNewAsset(assetDto);
        return new ResponseEntity<>(savedAsset, HttpStatus.CREATED);
    }

    //GET - Get All Assets REST API
    @GetMapping
    public ResponseEntity<List<AssetDto>> getAllAssets() {
        List<AssetDto> asset = assetService.getAllAssets();
        return ResponseEntity.ok(asset);
    }

    //GET - Get Asset By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable("id") Long id){
        Asset asset = assetRepository.findAllById(id)
                .orElseThrow(()->new RuntimeException("Asset does not exist with Id:" + id));
        return ResponseEntity.ok(asset);
    }

    //UPDATE - Update Asset REST API
    @PutMapping("{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable("id") long id,
                                             @RequestBody Asset assetDetails){
        Asset updateAsset = assetRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Asset does not exist with id: " + id));

        updateAsset.setAssetNumber(assetDetails.getAssetNumber());
        updateAsset.setBrand(assetDetails.getBrand());
        updateAsset.setModel(assetDetails.getModel());
        updateAsset.setType(assetDetails.getType());
        updateAsset.setSerialNumber(assetDetails.getSerialNumber());
        updateAsset.setLocation(assetDetails.getLocation());
        updateAsset.setRackNumber(assetDetails.getRackNumber());

        assetRepository.save(updateAsset);
        return ResponseEntity.ok(updateAsset);
    }

    //DELETE - Delete Asset REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("id") Long assetId){
        assetService.deleteAsset(assetId);
        return ResponseEntity.ok("Asset Deleted Successfully");
    }

}
