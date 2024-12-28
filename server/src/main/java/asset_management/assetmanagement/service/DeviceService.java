package asset_management.assetmanagement.service;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.dto.DeviceDto;

import java.util.List;

public interface DeviceService {

    DeviceDto createNewDevice(DeviceDto deviceDto);

    List<DeviceDto> getAllDevices();
}
