package demo.asset.management.repository;

import demo.asset.management.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findAllById(Long AssetId);
}
