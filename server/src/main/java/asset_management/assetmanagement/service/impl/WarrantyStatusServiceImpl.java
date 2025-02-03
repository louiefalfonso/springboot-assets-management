package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.WarrantyStatusDto;
import asset_management.assetmanagement.entity.WarrantyStatus;
import asset_management.assetmanagement.repository.WarrantyStatusRepository;
import asset_management.assetmanagement.service.WarrantyStatusService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarrantyStatusServiceImpl implements WarrantyStatusService {

    private WarrantyStatusRepository warrantyStatusRepository;
    private ModelMapper modelMapper;

    // REST API - Create New Warranty Status
    @Override
    public WarrantyStatusDto createWarrantyStatus(WarrantyStatusDto warrantyStatusDto) {
        WarrantyStatus warrantyStatus = modelMapper.map(warrantyStatusDto, WarrantyStatus.class);
        WarrantyStatus savedWarrantyStatus = warrantyStatusRepository.save(warrantyStatus);
        return modelMapper.map(savedWarrantyStatus, WarrantyStatusDto.class);
    }

    // REST API - Get All Warranty Statuses
    @Override
    public List<WarrantyStatusDto> getAllWarrantyStatuses() {
        List<WarrantyStatus> warrantyStatuses = warrantyStatusRepository.findAll();
        return warrantyStatuses.stream().map((warrantyStatus)-> modelMapper.map(warrantyStatus, WarrantyStatusDto.class))
                .collect(Collectors.toList());
    }

    // REST API - Get Warranty Status By ID
    @Override
    public WarrantyStatusDto getWarrantyStatusById(Long wStatusId) {
        WarrantyStatus warrantyStatus = warrantyStatusRepository.findAllById(wStatusId)
                .orElseThrow(()-> new RuntimeException("Warranty Status doesn't exist with a given Id:" + wStatusId));
        return  modelMapper.map(warrantyStatus, WarrantyStatusDto.class);
    }

    // REST API - Update Warranty Status
    @Override
    public WarrantyStatusDto updateWarrantyStatus(Long wStatusId, WarrantyStatusDto updateWarrantyStatus) {
        WarrantyStatus warrantyStatus = warrantyStatusRepository.findAllById(wStatusId)
                .orElseThrow(()-> new RuntimeException("Warranty Status doesn't exist with a given Id:" + wStatusId));

        warrantyStatus.setStatus(updateWarrantyStatus.getStatus());
        warrantyStatus.setWarrantyExpiry(updateWarrantyStatus.getWarrantyExpiry());

        WarrantyStatus updateWarrantyStatusObj = warrantyStatusRepository.save(warrantyStatus);
        return modelMapper.map(updateWarrantyStatusObj, WarrantyStatusDto.class);
    }

    // REST API - Delete Warranty Status
    @Override
    public void deleteWarrantyStatus(Long wStatusId) {
        WarrantyStatus warrantyStatus = warrantyStatusRepository.findAllById(wStatusId)
                .orElseThrow(()->new RuntimeException("Warranty Status  doesn't exist with given id:" + wStatusId));
        warrantyStatusRepository.deleteById(wStatusId);
    }

}
