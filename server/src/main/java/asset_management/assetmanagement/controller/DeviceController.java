package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.dto.DeviceDto;
import asset_management.assetmanagement.repository.DeviceRepository;
import asset_management.assetmanagement.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;

    public DeviceController(DeviceService deviceService, DeviceRepository deviceRepository) {
        this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
    }

    //POST - Create New Device REST API
    @PostMapping
    public ResponseEntity<DeviceDto> createNewDevice (@RequestBody DeviceDto deviceDto){
        DeviceDto savedDevice = deviceService.createNewDevice(deviceDto);
        return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
    }

    //GET - Get All Devices REST API
    @GetMapping
    public ResponseEntity<List<DeviceDto>> getAllDevices(){
        List<DeviceDto> device = deviceService.getAllDevices();
        return ResponseEntity.ok(device);
    }

}