package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.DeviceDto;
import asset_management.assetmanagement.entity.Device;
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

    //GET - Get Device By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable("id") Long id){
        Device device = deviceRepository.findAllById(id)
                .orElseThrow(()-> new RuntimeException("Device does not exist with Id:" + id));
        return ResponseEntity.ok(device);
    }

    //PUT - Archiving a Device REST API
    @PutMapping("/{id}/archive")
    public ResponseEntity<Void> archiveDevice(@PathVariable("id") Long id){
        deviceService.archiveDevice(id);
        return ResponseEntity.noContent().build();
    }

    //GET -Get All Archived Devices REST API
    @GetMapping("/archived")
    public ResponseEntity<List<DeviceDto>> getAllArchivedDevices(){
        List<DeviceDto> archivedDevices = deviceService.getAllArchivedDevices();
        return ResponseEntity.ok(archivedDevices);
    }

    //GET -Get Archived Device By ID REST API
    @GetMapping("/archived/{id}")
    public ResponseEntity<DeviceDto> getArchivedDeviceById(@PathVariable Long id) {
        DeviceDto archivedDevice = deviceService.getArchivedDeviceById(id);
        return ResponseEntity.ok(archivedDevice);
    }


    //DELETE - Delete Device REST API
    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteDevice(@PathVariable("id") Long deviceId){
        deviceService.deleteDevice(deviceId);
        return ResponseEntity.ok("Device Deleted Successfully");
    }

}