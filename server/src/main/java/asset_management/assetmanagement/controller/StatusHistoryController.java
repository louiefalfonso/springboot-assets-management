package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.StatusHistoryDto;
import asset_management.assetmanagement.entity.StatusHistory;
import asset_management.assetmanagement.repository.StatusHistoryRepository;
import asset_management.assetmanagement.service.StatusHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/status-history")
public class StatusHistoryController {

    private StatusHistoryRepository statusHistoryRepository;
    private StatusHistoryService statusHistoryService;

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

    //UPDATE - Update Status History REST API
    @PutMapping("{id}")
    public ResponseEntity<StatusHistory> updateStatusHistory(@PathVariable("id") long id,
                                                             @RequestBody StatusHistory sHistoryDetails){
        StatusHistory updateStatusHistory = statusHistoryRepository.findAllById(id)
                .orElseThrow(()->new RuntimeException("Status History does not exist with id: " + id));

        updateStatusHistory.setStatus(sHistoryDetails.getStatus());
        updateStatusHistory.setDateUpdated(sHistoryDetails.getDateUpdated());

        statusHistoryRepository.save(updateStatusHistory);
        return ResponseEntity.ok(updateStatusHistory);
    }

    //DELETE - Delete Status History REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStatusHistory(@PathVariable("id") Long sHistoryId){
        statusHistoryService.deleteStatusHistory(sHistoryId);
        return ResponseEntity.ok("Status History Deleted Successfully");
    }


}
