package asset_management.assetmanagement.repository;

import asset_management.assetmanagement.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    boolean existsByAssetNumber (String assetNumber);

    Optional<Asset> findAllById (Long AssetId);

}
