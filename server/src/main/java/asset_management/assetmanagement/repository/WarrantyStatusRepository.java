package asset_management.assetmanagement.repository;

import asset_management.assetmanagement.entity.WarrantyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarrantyStatusRepository extends JpaRepository<WarrantyStatus, Long> {

    Optional<WarrantyStatus> findAllById(Long WarrantyStatusId);
}
