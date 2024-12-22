package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.StatusTrackingDto;
import asset_management.assetmanagement.repository.StatusTrackingRepository;
import asset_management.assetmanagement.service.StatusTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
