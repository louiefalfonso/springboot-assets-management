package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.StatusTrackingDto;
import asset_management.assetmanagement.entity.StatusTracking;
import asset_management.assetmanagement.repository.StatusTrackingRepository;
import asset_management.assetmanagement.service.StatusTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/statustracking")
public class StatusTrackingController {

    private final StatusTrackingRepository statusTrackingRepository;
    private final StatusTrackingService statusTrackingService;

    public StatusTrackingController(StatusTrackingRepository statusTrackingRepository, StatusTrackingService statusTrackingService) {
        this.statusTrackingRepository = statusTrackingRepository;
        this.statusTrackingService = statusTrackingService;
    }

    //POST - Create New Status Tracking REST API
    @PostMapping
    public ResponseEntity<StatusTrackingDto> createStatusTracking(@RequestBody StatusTrackingDto statusTrackingDto){
        StatusTrackingDto savedStatusTracking = statusTrackingService.createStatusTracking(statusTrackingDto);
        return new ResponseEntity<>(savedStatusTracking, HttpStatus.CREATED);
    }

    //GET - Get All Status Tracking REST API
    @GetMapping
    public ResponseEntity<List<StatusTrackingDto>> getAllStatusTracking(){
        List<StatusTrackingDto> statusTracking = statusTrackingService.getAllStatusTracking();
        return ResponseEntity.ok(statusTracking);
    }

    //GET - Get Status Tracking By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<StatusTrackingDto> getStatusTrackingById(@PathVariable ("id") Long id){
        StatusTrackingDto sTrackingDto = statusTrackingService.getStatusTrackingById(id);
        return ResponseEntity.ok(sTrackingDto);
    }

    //UPDATE - Update Status Tracking REST API
    @PutMapping("{id}")
    public ResponseEntity<StatusTracking> updateStatusTracking(@PathVariable("id") long id,
                                                               @RequestBody StatusTracking sTrackingDetails){
        StatusTracking updateStatusTracking = statusTrackingRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Status Tracking does not exist with id: " + id));

        updateStatusTracking.setStatus(sTrackingDetails.getStatus());

        statusTrackingRepository.save(updateStatusTracking);
        return ResponseEntity.ok(updateStatusTracking);
    }


    //DELETE - Delete Status Tracking REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStatusTracking(@PathVariable("id") Long sTrackingId){
        statusTrackingService.deleteStatusTracking(sTrackingId);
        return ResponseEntity.ok("Status Tracking Deleted Successfully");
    }
}
