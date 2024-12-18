package demo.asset.management.service.impl;

import demo.asset.management.dto.AssetDto;
import demo.asset.management.entity.Asset;
import demo.asset.management.repository.AssetRepository;
import demo.asset.management.service.AssetService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;
    private ModelMapper modelMapper;

    // REST API - Create New Assets
    @Override
    public AssetDto createNewAssets(AssetDto assetDto) {
        if (assetRepository.existsByAssetNumber(assetDto.getAssetNumber())) {
            throw new RuntimeException("Asset number already exists");
        }
        Asset asset = modelMapper.map(assetDto, Asset.class);
        Asset savedAsset = assetRepository.save(asset);
        return modelMapper.map(savedAsset, AssetDto.class);
    }


    // REST API - Get All Assets
    @Override
    public List<AssetDto> getAllAssets() {
       List<Asset> assets = assetRepository.findAll();
       return assets.stream().map((asset)-> modelMapper.map(asset,AssetDto.class))
               .collect(Collectors.toList());
    }

    // REST API - Get Booking By ID
    @Override
    public AssetDto getAssetById(Long assetId) {
        Asset asset = assetRepository.findAllById(assetId)
                .orElseThrow(()->new RuntimeException("Asset doesn't exist with a given Id:" + assetId));
        return modelMapper.map(asset, AssetDto.class);
    }

    // REST API - Update Existing Asset
    @Override
    public AssetDto updateAsset(Long assetId, AssetDto updateAsset) {
       Asset asset = assetRepository.findAllById(assetId)
               .orElseThrow(()->new RuntimeException("Asset doesn't exist with given id:" + assetId));

       asset.setAssetNumber(updateAsset.getAssetNumber());
       asset.setBrand(updateAsset.getBrand());
       asset.setModel(updateAsset.getModel());
       asset.setType(updateAsset.getType());
       asset.setSerialNumber(updateAsset.getSerialNumber());
       asset.setLocation(updateAsset.getLocation());
       asset.setRackNumber(updateAsset.getRackNumber());

       Asset updateAssetObj = assetRepository.save(asset);
       return modelMapper.map(updateAssetObj, AssetDto.class);
    }

    // REST API - Delete Asset By ID
    @Override
    public void deleteAsset(Long assetId) {
        assetRepository.findAllById(assetId)
                .orElseThrow(()->new RuntimeException("Asset doesn't exist with given id:" + assetId));
        assetRepository.deleteById(assetId);
    }

}
