package asset_management.assetmanagement.repository;

import asset_management.assetmanagement.entity.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusHistoryRepository extends JpaRepository <StatusHistory, Long> {

    Optional<StatusHistory> findAllById(Long StatusHistoryId);
}
