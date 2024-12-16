package demo.asset.management.controller;

import demo.asset.management.dto.AssetDto;
import demo.asset.management.entity.Asset;
import demo.asset.management.repository.AssetRepository;
import demo.asset.management.service.AssetService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;
    private AssetService assetService;
    private ModelMapper modelMapper;


    // REST API - POST : Create New Assets
    @PostMapping
    public ResponseEntity<AssetDto> createNewAssets(@RequestBody AssetDto assetDto){
        AssetDto savedAsset = assetService.createNewAssets(assetDto);
        return new ResponseEntity<>(savedAsset, HttpStatus.CREATED);
    }

    // REST API - GET: Get All Assets
    @GetMapping
    public ResponseEntity<List<AssetDto>> getAllAssets(){
        List<AssetDto> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }


    // REST API - GET:  Get Asset by ID
    @GetMapping("{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable("id")Long id){
        Asset asset = assetRepository.findAllById(id)
                .orElseThrow(()-> new RuntimeException("Asset does not exist with Id:" + id));
        return ResponseEntity.ok(asset);
    }


    // REST API - DELETE: Delete Asset
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("id") Long assetId){
        assetService.deleteAsset(assetId);
        return ResponseEntity.ok("Asset Deleted Successfully");
    }

    // REST API - Update Asset
    @PutMapping("{id}")
    public ResponseEntity<AssetDto> updateAsset(@PathVariable("id") long id,
                                                 @RequestBody Asset assetDetails){
        Asset updateAsset = assetRepository.findAllById(id)
                .orElseThrow(()->new RuntimeException("Asset does not exist with id: " + id));

        updateAsset.setAssetNumber(assetDetails.getAssetNumber());
        updateAsset.setBrand(assetDetails.getBrand());
        updateAsset.setModel(assetDetails.getModel());
        updateAsset.setType(assetDetails.getType());
        updateAsset.setSerialNumber(assetDetails.getSerialNumber());
        updateAsset.setLocation(assetDetails.getLocation());
        updateAsset.setRackNumber(assetDetails.getRackNumber());

        assetRepository.save(updateAsset);
        AssetDto updatedAssetDto = modelMapper.map(updateAsset, AssetDto.class);
        return ResponseEntity.ok(updatedAssetDto);
    }


}