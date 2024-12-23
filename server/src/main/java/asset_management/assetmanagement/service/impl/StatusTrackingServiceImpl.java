package asset_management.assetmanagement.service.impl;

import asset_management.assetmanagement.dto.StatusTrackingDto;
import asset_management.assetmanagement.entity.StatusTracking;
import asset_management.assetmanagement.repository.StatusTrackingRepository;
import asset_management.assetmanagement.service.StatusTrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusTrackingServiceImpl implements StatusTrackingService {

    private final StatusTrackingRepository statusTrackingRepository;
    private final ModelMapper modelMapper;

    public StatusTrackingServiceImpl(StatusTrackingRepository statusTrackingRepository, ModelMapper modelMapper) {
        this.statusTrackingRepository = statusTrackingRepository;
        this.modelMapper = modelMapper;
    }

    // REST API - Create New Status Tracking
    @Override
    public StatusTrackingDto createStatusTracking(StatusTrackingDto statusTrackingDto) {
        StatusTracking statusTracking = modelMapper.map(statusTrackingDto, StatusTracking.class);
        StatusTracking savedStatusTracking = statusTrackingRepository.save(statusTracking);
        return modelMapper.map(savedStatusTracking, StatusTrackingDto.class);
    }

    // REST API - Get All Status Tracking
    @Override
    public List<StatusTrackingDto> getAllStatusTracking() {
        List<StatusTracking> statusTrackings = statusTrackingRepository.findAll();
        return statusTrackings.stream().map((statusTracking)-> modelMapper.map(statusTracking, StatusTrackingDto.class))
                .collect(Collectors.toList());
    }


}
