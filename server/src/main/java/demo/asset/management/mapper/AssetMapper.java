package demo.asset.management.mapper;

import demo.asset.management.dto.AssetDto;
import demo.asset.management.entity.Asset;

public class AssetMapper {

    public static Asset mapToAsset(AssetDto assetDto){
        Asset asset = new Asset(
                assetDto.getId(),
                assetDto.getAssetNumber(),
                assetDto.getBrand(),
                assetDto.getType(),
                assetDto.getSerialNumber(),
                assetDto.getLocation(),
                assetDto.getModel(),
                assetDto.getRackNumber()
        );
        return asset;
    }

    public  static AssetDto mapToAssetDto(Asset asset){
        AssetDto assetDto = new AssetDto(
                asset.getId(),
                asset.getAssetNumber(),
                asset.getBrand(),
                asset.getType(),
                asset.getSerialNumber(),
                asset.getLocation(),
                asset.getModel(),
                asset.getRackNumber()
        );
        return assetDto;
    }
}
