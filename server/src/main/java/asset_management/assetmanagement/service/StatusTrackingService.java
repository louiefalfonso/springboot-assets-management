package asset_management.assetmanagement.service;

import asset_management.assetmanagement.dto.StatusTrackingDto;

import java.util.List;

public interface StatusTrackingService {

    StatusTrackingDto createStatusTracking( StatusTrackingDto statusTrackingDto);

    List<StatusTrackingDto> getAllStatusTracking();

    StatusTrackingDto getStatusTrackingById (Long sTrackingId);

    StatusTrackingDto updateStatusTracking(Long sTrackingId, StatusTrackingDto updateWarrantyStatus);

    void deleteStatusTracking(Long sTrackingId);
}
