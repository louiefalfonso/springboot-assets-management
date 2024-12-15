package demo.asset.management.service.impl;

import demo.asset.management.dto.AssetDto;
import demo.asset.management.entity.Asset;
import demo.asset.management.mapper.AssetMapper;
import demo.asset.management.repository.AssetRepository;
import demo.asset.management.service.AssetService;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;
    public AssetServiceImpl(AssetRepository assetRepository){
        this.assetRepository = assetRepository;
    }

    // REST API - Create New Assets
    @Override
    public AssetDto createNewAssets(AssetDto assetDto) {
        Asset asset = AssetMapper.mapToAsset(assetDto);
        Asset savedAssets = assetRepository.save(asset);
        return AssetMapper.mapToAssetDto(savedAssets);
    }

}
