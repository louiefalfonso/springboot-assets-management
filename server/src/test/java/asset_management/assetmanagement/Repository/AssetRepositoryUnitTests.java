package asset_management.assetmanagement.Repository;

import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AssetRepositoryUnitTests {

    @Autowired
    private AssetRepository assetRepository;

    @Test
    @Order(1)
    @DisplayName("Test 1: Save Asset Test")
    public void saveAssetTest(){
        Asset asset = Asset.builder()
                .assetNumber("C0000001")
                .brand("Lenovo")
                .model("Acer 314 14 Inch Chromebook (CB314-2H)")
                .type("Chromebook")
                .serialNumber("MT8183C")
                .location("CCG-IT-DEPT")
                .rackNumber("MT8183C")
                .build();

        Asset savedAsset = assetRepository.save(asset);
        Assertions.assertThat(asset.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Get Asset Test")
    public void getAssetTest(){
        // Create an asset with ID 1L if it doesn't exist
        Asset asset = assetRepository.findAllById(1L).orElseGet(()-> {
            Asset newAsset = new Asset();
            newAsset.setId(1L);
            return assetRepository.save(newAsset);
        });

        // Verify that the retrieved asset's ID is 1L
        Assertions.assertThat(asset.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Get All Assets Test")
    public void getListOfAssets(){
        List<Asset> assets = assetRepository.findAll();
        Assertions.assertThat(assets.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Test for Asset Not Found")
    public void assetNotFoundTest(){
        // Try to find an asset with an ID that doesn't exist
        Optional<Asset> assetOptional = assetRepository.findById(999L);

        // Verify that the Asset is not found
        Assertions.assertThat(assetOptional).isEmpty();

    }

}
