package asset_management.assetmanagement.Repository;

import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AssetRepositoryUnitTests {

    @Autowired
    private AssetRepository assetRepository;

    @Test
    @Order(1)
    @DisplayName("Test 1: Create New Asset Test")
    public void createNewAssetTest() {
        // Create a new asset object
        Asset asset = Asset.builder()
                .assetNumber("C0000002")
                .brand("Dell")
                .model("Latitude 5420")
                .type("Laptop")
                .serialNumber("SN123456")
                .location("HR-DEPT")
                .rackNumber("RACK001")
                .build();

        // Save the asset
        Asset savedAsset = assetRepository.save(asset);

        // Verify that the asset is saved
        Assertions.assertThat(savedAsset.getId()).isGreaterThan(0);
        Assertions.assertThat(savedAsset.getAssetNumber()).isEqualTo("C0000002");
        Assertions.assertThat(savedAsset.getBrand()).isEqualTo("Dell");
        Assertions.assertThat(savedAsset.getModel()).isEqualTo("Latitude 5420");
        Assertions.assertThat(savedAsset.getType()).isEqualTo("Laptop");
        Assertions.assertThat(savedAsset.getSerialNumber()).isEqualTo("SN123456");
        Assertions.assertThat(savedAsset.getLocation()).isEqualTo("HR-DEPT");
        Assertions.assertThat(savedAsset.getRackNumber()).isEqualTo("RACK001");
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Save Asset Test")
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
    @Order(3)
    @DisplayName("Test 3: Get Asset By ID Test")
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
    @Order(4)
    @DisplayName("Test 4: Get All Assets Test")
    public void getListOfAssets(){
        List<Asset> assets = assetRepository.findAll();
        Assertions.assertThat(assets.size()).isGreaterThan(0);
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Test for Asset Not Found")
    public void assetNotFoundTest(){
        // Try to find an asset with an ID that doesn't exist
        Optional<Asset> assetOptional = assetRepository.findById(999L);

        // Verify that the Asset is not found
        Assertions.assertThat(assetOptional).isEmpty();

    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Test for Asset Not Found - NoSuchElementException")
    public void assetNotFoundSuchElementExceptionTest(){
        // Try to find an asset with an ID that doesn't exist
        Optional<Asset> assetOptional = assetRepository.findById(999L);

        // Verify that a NoSuchElementException is thrown when trying to get the Asset
        Assertions.assertThatThrownBy(assetOptional::get).isInstanceOf(NoSuchElementException.class);

    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Test for Asset Expected Strings")
    public void toStringTest(){
        Asset asset = Asset.builder()
                .id(1L)
                .assetNumber("C0000001")
                .brand("Lenovo")
                .model("Acer 314 14 Inch Chromebook (CB314-2H)")
                .type("Chromebook")
                .serialNumber("MT8183C")
                .location("CCG-IT-DEPT")
                .rackNumber("MT8183C")
                .build();
        String expectedToString = "Asset(id=1, assetNumber=C0000001, brand=Lenovo, model=Acer 314 14 Inch Chromebook (CB314-2H), type=Chromebook, serialNumber=MT8183C, location=CCG-IT-DEPT, rackNumber=MT8183C, dateCreated=null, statusHistory=null, statusTracking=null, warrantyStatus=null)";

        Assertions.assertThat(asset.toString()).isEqualTo(expectedToString);


    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Update Asset")
    public void updateAsset(){
        Asset asset = assetRepository.findAllById(1L).get();
        asset.setAssetNumber("C0000001");
        asset.setBrand("Lenovo");
        asset.setModel("Acer 314 14 Inch Chromebook (CB314-2H)");
        asset.setType("Chromebook");
        asset.setSerialNumber("MT8183C");
        asset.setLocation("CCG-IT-DEPT");
        asset.setRackNumber("785973");

        Asset assetUpdated = assetRepository.save(asset);

        Assertions.assertThat(assetUpdated.getAssetNumber()).isEqualTo("C0000001");
        Assertions.assertThat(assetUpdated.getBrand()).isEqualTo("Lenovo");
        Assertions.assertThat(assetUpdated.getModel()).isEqualTo("Acer 314 14 Inch Chromebook (CB314-2H)");
        Assertions.assertThat(assetUpdated.getType()).isEqualTo("Chromebook");
        Assertions.assertThat(assetUpdated.getSerialNumber()).isEqualTo("MT8183C");
        Assertions.assertThat(assetUpdated.getLocation()).isEqualTo("CCG-IT-DEPT");
        Assertions.assertThat(assetUpdated.getRackNumber()).isEqualTo("785973");
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Delete Asset")
    public void deleteAsset() {
        // Retrieve the asset to be deleted
        Asset asset = assetRepository.findById(1L).orElseThrow(() -> new RuntimeException("Asset not found"));

        // Delete the asset
        assetRepository.deleteById(1L);

        // Verify that the asset is deleted
        Optional<Asset> deletedAsset = assetRepository.findById(1L);
        Assertions.assertThat(deletedAsset).isEmpty();
    }

}
