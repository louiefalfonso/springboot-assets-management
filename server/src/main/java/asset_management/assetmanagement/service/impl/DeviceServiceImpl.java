package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.DeviceDto;
import asset_management.assetmanagement.entity.Device;
import asset_management.assetmanagement.repository.DeviceRepository;
import asset_management.assetmanagement.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModelMapper modelMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.modelMapper = modelMapper;
    }

    // REST API - Create New Device
    @Override
    public DeviceDto createNewDevice(DeviceDto deviceDto) {
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

    // REST API - Get Device By ID
    @Override
    public DeviceDto getDeviceById(Long deviceId) {
        Device device = deviceRepository.findAllById(deviceId)
                .orElseThrow(()-> new RuntimeException("Device doesn't exist with a given Id:" + deviceId));
        return modelMapper.map(device, DeviceDto.class);
    }

    // REST API - Archiving a Device
    @Override
    public void archiveDevice(Long deviceId) {
        Device device = deviceRepository.findAllById(deviceId)
                .orElseThrow(()->new RuntimeException("Device doesn't exist with a given Id:" + deviceId));
        device.setArchived(true);
        deviceRepository.save(device);
    }

    // REST API - Get All Archived Devices
    @Override
    public List<DeviceDto> getAllArchivedDevices() {
        List<Device> archivedDevices = deviceRepository.findByArchived(true);
        return archivedDevices.stream().map((device)-> modelMapper.map(device,DeviceDto.class))
                .collect(Collectors.toList());
    }

    // REST API - Get Archived Device By ID
    @Override
    public DeviceDto getArchivedDeviceById(Long id) {
        Device device = deviceRepository.findByIdAndArchived(id, true)
                .orElseThrow(() -> new RuntimeException("Archived Device doesn't exist with a given Id:" + id));
        return modelMapper.map(device, DeviceDto.class);
    }


}