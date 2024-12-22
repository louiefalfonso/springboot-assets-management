package asset_management.assetmanagement.service;

import asset_management.assetmanagement.dto.StatusHistoryDto;

import java.util.List;

public interface StatusHistoryService {

    StatusHistoryDto createStatusHistory (StatusHistoryDto statusHistoryDto);

    List<StatusHistoryDto> getAllStatusHistory();

    StatusHistoryDto getStatusHistoryById(Long sHistoryId);

    StatusHistoryDto updateStatusHistory(Long sHistoryId, StatusHistoryDto updateWarrantyStatus);

    void deleteStatusHistory(Long sHistoryId);
}
