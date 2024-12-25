package asset_management.assetmanagement.repository;

import asset_management.assetmanagement.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findAllById(Long deviceId);
}
