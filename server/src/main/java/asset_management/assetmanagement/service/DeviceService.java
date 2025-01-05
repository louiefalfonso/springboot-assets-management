package asset_management.assetmanagement.service;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.dto.DeviceDto;

import java.util.List;

public interface DeviceService {

    DeviceDto createNewDevice(DeviceDto deviceDto);

    List<DeviceDto> getAllDevices();

    DeviceDto getDeviceById(Long deviceId);

    void deleteDevice(Long deviceId);

    void archiveDevice(Long deviceId);

    List<DeviceDto> getAllArchivedDevices();

    DeviceDto getArchivedDeviceById(Long id);

    List<DeviceDto> getAllDeletedDevices();

    DeviceDto getDeletedDeviceById(Long id);

}
