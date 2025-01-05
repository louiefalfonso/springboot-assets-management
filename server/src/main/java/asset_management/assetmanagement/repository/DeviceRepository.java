package asset_management.assetmanagement.repository;

import asset_management.assetmanagement.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findAllById(Long deviceId);

    List<Device> findByArchived(boolean archived);

    Optional<Device> findByIdAndArchived(Long id, boolean archived);

    List<Device> findByDeleted(boolean deleted);

    Optional<Device> findByIdAndDeleted(Long id, boolean deleted);
}