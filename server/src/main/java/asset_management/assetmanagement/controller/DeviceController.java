package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.DeviceDto;
import asset_management.assetmanagement.entity.Device;
import asset_management.assetmanagement.repository.DeviceRepository;
import asset_management.assetmanagement.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private DeviceService deviceService;
    private DeviceRepository deviceRepository;

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
    public ResponseEntity<String> archiveDevice(@PathVariable("id") Long id){
        deviceService.archiveDevice(id);
        return ResponseEntity.ok("Device Archived Successfully");
    }

    //GET - Get All Archived Devices REST API
    @GetMapping("/archived")
    public ResponseEntity<List<DeviceDto>> getAllArchivedDevices(){
        List<DeviceDto> archivedDevices = deviceService.getAllArchivedDevices();
        return ResponseEntity.ok(archivedDevices);
    }

    //GET - Get Archived Device By ID REST API
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

    //GET - Get All Deleted Devices REST API
    @GetMapping("/deleted")
    public ResponseEntity<List<DeviceDto>> getAllDeletedDevices() {
        List<DeviceDto> deletedDevices = deviceService.getAllDeletedDevices();
        return ResponseEntity.ok(deletedDevices);
    }

    //GET - Get Deleted Device By ID REST API
    @GetMapping("/deleted/{id}")
    public ResponseEntity<DeviceDto> getDeletedDeviceById(@PathVariable Long id) {
        DeviceDto deletedDevice = deviceService.getDeletedDeviceById(id);
        return ResponseEntity.ok(deletedDevice);
    }

}