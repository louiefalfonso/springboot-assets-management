package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.WarrantyStatusDto;
import asset_management.assetmanagement.entity.WarrantyStatus;
import asset_management.assetmanagement.repository.WarrantyStatusRepository;
import asset_management.assetmanagement.service.WarrantyStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarrantyStatusServiceImpl implements WarrantyStatusService {

    private final WarrantyStatusRepository warrantyStatusRepository;
    private final ModelMapper modelMapper;

    public WarrantyStatusServiceImpl(WarrantyStatusRepository warrantyStatusRepository, ModelMapper modelMapper) {
        this.warrantyStatusRepository = warrantyStatusRepository;
        this.modelMapper = modelMapper;
    }

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
    
}
