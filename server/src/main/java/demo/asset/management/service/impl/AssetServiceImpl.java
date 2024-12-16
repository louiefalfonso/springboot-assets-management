package demo.asset.management.service.impl;

import demo.asset.management.dto.AssetDto;
import demo.asset.management.entity.Asset;
import demo.asset.management.mapper.AssetMapper;
import demo.asset.management.repository.AssetRepository;
import demo.asset.management.service.AssetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // REST API - Get All Assets
    @Override
    public List<AssetDto> getAllAssets() {
        List<Asset> assets = assetRepository.findAll();
        return assets.stream().map((asset)-> AssetMapper.mapToAssetDto(asset))
                .collect(Collectors.toList());
    }

    // REST API - Get Asset by ID
    @Override
    public AssetDto getAssetById(Long id) {
        Asset asset = assetRepository.findById(id).orElseThrow(()->new RuntimeException("Assets does not exists"));
        return AssetMapper.mapToAssetDto(asset);
    }

    // REST API - Delete Asset By ID
    @Override
    public void deleteAsset(Long id) {
        Asset asset = assetRepository.findById(id).orElseThrow(()-> new RuntimeException("Assets does not exists"));
        assetRepository.deleteById(id);
    }

    // REST API - Update Asset By ID
    @Override
    public AssetDto updateAsset(Long id, AssetDto updatedAsset) {
        Asset asset = assetRepository.findById(id).orElseThrow(()-> new RuntimeException("Assets does not exists"));

        asset.setAssetNumber(updatedAsset.getAssetNumber());
        asset.setBrand(updatedAsset.getBrand());
        asset.setModel(updatedAsset.getModel());
        asset.setType(updatedAsset.getType());
        asset.setSerialNumber(updatedAsset.getSerialNumber());
        asset.setLocation(updatedAsset.getLocation());
        asset.setRackNumber(updatedAsset.getRackNumber());

        Asset updatedAssetObj = assetRepository.save(asset);
        return AssetMapper.mapToAssetDto(updatedAssetObj);
    }
}
