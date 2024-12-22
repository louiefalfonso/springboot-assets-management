package asset_management.assetmanagement.repository;

import asset_management.assetmanagement.entity.StatusTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusTrackingRepository extends JpaRepository<StatusTracking, Long> {

    Optional<StatusTracking> findAllById(Long StatusTrackingId);
}
