package asset_management.assetmanagement.service;

import asset_management.assetmanagement.dto.WarrantyStatusDto;

import java.util.List;

public interface WarrantyStatusService {

    WarrantyStatusDto createWarrantyStatus (WarrantyStatusDto warrantyStatusDto);

    List<WarrantyStatusDto> getAllWarrantyStatuses();

    WarrantyStatusDto  getWarrantyStatusById (Long wStatusId);

    WarrantyStatusDto updateWarrantyStatus (Long wStatusId, WarrantyStatusDto updateWarrantyStatus);

    void deleteWarrantyStatus (Long wStatusId);
}
