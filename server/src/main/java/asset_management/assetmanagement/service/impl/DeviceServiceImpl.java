package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.dto.DeviceDto;
import asset_management.assetmanagement.entity.Device;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.repository.DeviceRepository;
import asset_management.assetmanagement.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // REST API - Create New Device
    @Override
    public DeviceDto createNewDevice(DeviceDto deviceDto) {
       /* if (assetRepository.existsByAssetNumber(assetDto.getAssetNumber())) {
            throw new RuntimeException("Asset number already exists");
        }*/
        Device device = modelMapper.map(deviceDto, Device.class);
        Device savedDevice = deviceRepository.save(device);
        return modelMapper.map(savedDevice, DeviceDto.class);
    }

    // REST API - Get All Devices
    @Override
    public List<DeviceDto> getAllDevices() {
        List<Device> devices = deviceRepository.findAll();
        return devices.stream().map((device)-> modelMapper.map(device, DeviceDto.class))
                .collect(Collectors.toList());
    }

}
