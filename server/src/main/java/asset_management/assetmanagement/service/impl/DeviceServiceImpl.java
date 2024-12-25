package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.DeviceDto;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.repository.DeviceRepository;
import asset_management.assetmanagement.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final AssetRepository assetRepository;
    private final ModelMapper modelMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, AssetRepository assetRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.assetRepository = assetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeviceDto createNewDevice(DeviceDto deviceDto) {
        return null;
    }
}
