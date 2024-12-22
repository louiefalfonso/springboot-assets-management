package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.StatusHistoryDto;
import asset_management.assetmanagement.entity.StatusHistory;
import asset_management.assetmanagement.repository.StatusHistoryRepository;
import asset_management.assetmanagement.service.StatusHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryRepository statusHistoryRepository;
    private final ModelMapper modelMapper;

    public StatusHistoryServiceImpl( StatusHistoryRepository statusHistoryRepository, ModelMapper modelMapper) {
        this.statusHistoryRepository = statusHistoryRepository;
        this.modelMapper = modelMapper;

    }

    // REST API - Create New Status History
    @Override
    public StatusHistoryDto createStatusHistory(StatusHistoryDto statusHistoryDto) {
        StatusHistory statusHistory = modelMapper.map(statusHistoryDto, StatusHistory.class);
        StatusHistory savedStatusHistory = statusHistoryRepository.save(statusHistory);
        return modelMapper.map(savedStatusHistory, StatusHistoryDto.class);
    }

    // REST API - Get All Status History
    @Override
    public List<StatusHistoryDto> getAllStatusHistory() {
        List<StatusHistory> statusHistories = statusHistoryRepository.findAll();
        return  statusHistories.stream().map((statusHistory)-> modelMapper.map(statusHistory, StatusHistoryDto.class))
                .collect(Collectors.toList());
    }

    // REST API - Get Status History By ID
    @Override
    public StatusHistoryDto getStatusHistoryById(Long sHistoryId) {
        StatusHistory statusHistory = statusHistoryRepository.findAllById(sHistoryId)
                .orElseThrow(()-> new RuntimeException("Status History doesn't exist with a given Id:" + sHistoryId));
        return modelMapper.map(statusHistory, StatusHistoryDto.class);
    }

    // REST API - Update Status History
    @Override
    public StatusHistoryDto updateStatusHistory(Long sHistoryId, StatusHistoryDto updateWarrantyStatus) {
        StatusHistory statusHistory = statusHistoryRepository.findAllById(sHistoryId)
                .orElseThrow(()->new RuntimeException("Status History doesn't exist with a given Id:" + sHistoryId));

        statusHistory.setStatus(updateWarrantyStatus.getStatus());
        statusHistory.setDateUpdated(updateWarrantyStatus.getDateUpdated());

        StatusHistory updateStatusHistoryObj = statusHistoryRepository.save(statusHistory);
        return modelMapper.map(updateStatusHistoryObj, StatusHistoryDto.class);
    }

}
