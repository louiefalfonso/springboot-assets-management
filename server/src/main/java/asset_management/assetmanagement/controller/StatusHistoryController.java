package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.StatusHistoryDto;
import asset_management.assetmanagement.repository.StatusHistoryRepository;
import asset_management.assetmanagement.service.StatusHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/statushistory")
public class StatusHistoryController {

    private final StatusHistoryRepository statusHistoryRepository;
    private final StatusHistoryService statusHistoryService;

    public StatusHistoryController(StatusHistoryRepository statusHistoryRepository, StatusHistoryService statusHistoryService) {
        this.statusHistoryRepository = statusHistoryRepository;
        this.statusHistoryService = statusHistoryService;
    }

    //POST - Create New Status History REST API
    @PostMapping
    public ResponseEntity<StatusHistoryDto> createStatusHistory(@RequestBody StatusHistoryDto statusHistoryDto){
        StatusHistoryDto savedStatusHistory = statusHistoryService.createStatusHistory(statusHistoryDto);
        return new ResponseEntity<>(savedStatusHistory, HttpStatus.CREATED);
    }

    //GET - Get All Status History REST API
    @GetMapping
    public ResponseEntity<List<StatusHistoryDto>> getAllStatusHistory(){
        List<StatusHistoryDto> statusHistory = statusHistoryService.getAllStatusHistory();
        return ResponseEntity.ok(statusHistory);
    }

    //GET - Get Status History  By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<StatusHistoryDto> getStatusHistoryById(@PathVariable("id") Long id){
        StatusHistoryDto sHistoryDto = statusHistoryService.getStatusHistoryById(id);
        return ResponseEntity.ok(sHistoryDto);
    }

}
