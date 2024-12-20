package asset_management.assetmanagement.service;

import asset_management.assetmanagement.dto.AssetDto;

import java.util.List;

public interface AssetService {

    AssetDto createNewAsset (AssetDto assetDto);

    List<AssetDto> getAllAssets();

    AssetDto getAssetById (Long assetId);

    AssetDto updateAsset(Long assetId, AssetDto updateAsset);

    void deleteAsset(Long assetId);


}


