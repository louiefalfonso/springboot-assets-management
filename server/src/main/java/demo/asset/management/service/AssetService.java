package demo.asset.management.service;

import demo.asset.management.dto.AssetDto;

import java.util.List;

public interface AssetService {

    AssetDto createNewAssets(AssetDto assetDto);

    List<AssetDto> getAllAssets();

    AssetDto getAssetById(Long assetId);

    AssetDto updateAsset(Long assetId, AssetDto updateAsset);

    void deleteAsset(Long assetId);
}
