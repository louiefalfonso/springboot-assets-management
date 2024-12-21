package asset_management.assetmanagement.controller;

import asset_management.assetmanagement.dto.WarrantyStatusDto;
import asset_management.assetmanagement.entity.WarrantyStatus;
import asset_management.assetmanagement.repository.WarrantyStatusRepository;
import asset_management.assetmanagement.service.WarrantyStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/warrantystatus")
public class WarrantyStatusController {

    private final WarrantyStatusRepository warrantyStatusRepository;
    private final WarrantyStatusService warrantyStatusService;

    public WarrantyStatusController(WarrantyStatusRepository warrantyStatusRepository, WarrantyStatusService warrantyStatusService) {
        this.warrantyStatusRepository = warrantyStatusRepository;
        this.warrantyStatusService = warrantyStatusService;
    }

    //POST - Create New Warranty Status REST API
    @PostMapping
    public ResponseEntity<WarrantyStatusDto> createWarrantyStatus (@RequestBody WarrantyStatusDto warrantyStatusDto){
        WarrantyStatusDto savedWarrantyStatus = warrantyStatusService.createWarrantyStatus(warrantyStatusDto);
        return new ResponseEntity<>(savedWarrantyStatus, HttpStatus.CREATED);
    }

    //GET - Get All Warranty Statuses REST API
    @GetMapping
    public ResponseEntity<List<WarrantyStatusDto>> getAllWarrantyStatuses(){
        List<WarrantyStatusDto> warrantyStatus = warrantyStatusService.getAllWarrantyStatuses();
        return ResponseEntity.ok(warrantyStatus);
    }

    //GET - Get Warranty Status By ID REST API
    @GetMapping("{id}")
    public ResponseEntity<WarrantyStatusDto> getWarrantyStatusById(@PathVariable("id") Long id){
        WarrantyStatusDto wStatusDto = warrantyStatusService.getWarrantyStatusById(id);
        return ResponseEntity.ok(wStatusDto);
    }

    //UPDATE - Update Warranty Status REST API
    @PutMapping("{id}")
    public ResponseEntity<WarrantyStatus> updateWarrantyStatus (@PathVariable("id") long id,
                                                                   @RequestBody WarrantyStatus wStatusDetails) {
        WarrantyStatus updateWarrantyStatus = warrantyStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warranty Status does not exist with id: " + id));

        updateWarrantyStatus.setWarrantyExpiry(wStatusDetails.getWarrantyExpiry());
        updateWarrantyStatus.setStatus(wStatusDetails.getStatus());

        warrantyStatusRepository.save(updateWarrantyStatus);
        return ResponseEntity.ok(updateWarrantyStatus);
    }

    //DELETE - Delete Warranty Status REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWarrantyStatus(@PathVariable("id") Long wStatusId){
        warrantyStatusService.deleteWarrantyStatus(wStatusId);
        return ResponseEntity.ok("Warranty Status Deleted Successfully");
    }

}
