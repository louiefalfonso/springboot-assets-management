package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.service.AssetService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    public AssetServiceImpl(AssetRepository assetRepository, ModelMapper modelMapper) {
        this.assetRepository = assetRepository;
        this.modelMapper = modelMapper;
    }

    // REST API - Create New Asset
    @Override
    public AssetDto createNewAsset (AssetDto assetDto) {
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
        return assets.stream().map((asset)-> modelMapper.map(asset, AssetDto.class))
                .collect(Collectors.toList());
    }

    // REST API - Get Asset By Id
    @Override
    public AssetDto getAssetById (Long assetId) {
       Asset asset = assetRepository.findAllById(assetId)
               .orElseThrow(()->new RuntimeException("Asset doesn't exist with a given Id:" + assetId));
       return modelMapper.map(asset, AssetDto.class);
    }


}
