package asset_management.assetmanagement.Service;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.service.impl.AssetServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AssetServiceUnitTests {

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AssetServiceImpl assetService;

    @Test
    @Order(1)
    @DisplayName("Test 1: Create New Asset Successfully")
    public void createNewAsset_Success() {
        // Arrange
        AssetDto assetDto = new AssetDto();
        assetDto.setAssetNumber("C0000001");

        Asset asset = new Asset();
        asset.setAssetNumber("C0000001");

        when(assetRepository.existsByAssetNumber("C0000001")).thenReturn(false);
        when(modelMapper.map(assetDto, Asset.class)).thenReturn(asset);
        when(assetRepository.save(asset)).thenReturn(asset);
        when(modelMapper.map(asset, AssetDto.class)).thenReturn(assetDto);

        // Act
        AssetDto result = assetService.createNewAsset(assetDto);

        // Assert
        assertNotNull(result);
        assertEquals("C0000001", result.getAssetNumber());
        verify(assetRepository, times(1)).existsByAssetNumber("C0000001");
        verify(assetRepository, times(1)).save(asset);
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Create New Asset with Existing Asset Number")
    public void createNewAsset_AssetNumberExists() {
        // Arrange
        AssetDto assetDto = new AssetDto();
        assetDto.setAssetNumber("C0000001");

        when(assetRepository.existsByAssetNumber("C0000001")).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            assetService.createNewAsset(assetDto);
        });

        assertEquals("Asset number already exists", exception.getMessage());
        verify(assetRepository, times(1)).existsByAssetNumber("C0000001");
        verify(assetRepository, never()).save(any(Asset.class));
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Create New Asset with Null Input")
    public void createNewAsset_NullInput() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            assetService.createNewAsset(null);
        });

        verify(assetRepository, never()).existsByAssetNumber(anyString());
        verify(assetRepository, never()).save(any(Asset.class));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Get All Assets Successfully")
    public void getAllAssets_Success() {
        // Arrange
        Asset asset1 = new Asset();
        asset1.setAssetNumber("C0000001");

        Asset asset2 = new Asset();
        asset2.setAssetNumber("C0000002");

        List<Asset> assets = Arrays.asList(asset1, asset2);

        AssetDto assetDto1 = new AssetDto();
        assetDto1.setAssetNumber("C0000001");

        AssetDto assetDto2 = new AssetDto();
        assetDto2.setAssetNumber("C0000002");

        when(assetRepository.findAll()).thenReturn(assets);
        when(modelMapper.map(asset1, AssetDto.class)).thenReturn(assetDto1);
        when(modelMapper.map(asset2, AssetDto.class)).thenReturn(assetDto2);

        // Act
        List<AssetDto> result = assetService.getAllAssets();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("C0000001", result.get(0).getAssetNumber());
        assertEquals("C0000002", result.get(1).getAssetNumber());
        verify(assetRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(asset1, AssetDto.class);
        verify(modelMapper, times(1)).map(asset2, AssetDto.class);
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Get All Assets When No Assets Exist")
    public void getAllAssets_NoAssets() {
        // Arrange
        when(assetRepository.findAll()).thenReturn(List.of());

        // Act
        List<AssetDto> result = assetService.getAllAssets();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(assetRepository, times(1)).findAll();
        verify(modelMapper, never()).map(any(Asset.class), eq(AssetDto.class));
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Get All Assets with Null Repository Response")
    public void getAllAssets_NullRepositoryResponse() {
        // Arrange
        when(assetRepository.findAll()).thenReturn(List.of()); // Return an empty list instead of null

        // Act
        List<AssetDto> result = assetService.getAllAssets();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(assetRepository, times(1)).findAll();
        verify(modelMapper, never()).map(any(Asset.class), eq(AssetDto.class));
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Get Asset By Id Successfully")
    public void getAssetById_Success() {
        // Arrange
        Long assetId = 1L;
        Asset asset = new Asset();
        asset.setId(assetId);
        asset.setAssetNumber("C0000001");
        asset.setBrand("ACER");
        asset.setModel("Acer 314 14 Inch Chromebook (CB314-2H)");
        asset.setType("Chromebook");
        asset.setSerialNumber("MT8183C");
        asset.setLocation("CCG-IT-DEPT");
        asset.setRackNumber("R123");

        AssetDto assetDto = new AssetDto();
        assetDto.setId(assetId);
        assetDto.setAssetNumber("C0000001");
        assetDto.setBrand("ACER");
        assetDto.setModel("Acer 314 14 Inch Chromebook (CB314-2H");
        assetDto.setType("Chromebook");
        assetDto.setSerialNumber("MT8183C");
        assetDto.setLocation("CCG-IT-DEPT");
        assetDto.setRackNumber("R123");

        when(assetRepository.findAllById(assetId)).thenReturn(Optional.of(asset));
        when(modelMapper.map(asset, AssetDto.class)).thenReturn(assetDto);

        // Act
        AssetDto result = assetService.getAssetById(assetId);

        // Assert
        assertNotNull(result);
        assertEquals(assetId, result.getId());
        assertEquals("C0000001", result.getAssetNumber());
        assertEquals("ACER", result.getBrand());
        assertEquals("Acer 314 14 Inch Chromebook (CB314-2H", result.getModel());
        assertEquals("Chromebook", result.getType());
        assertEquals("MT8183C", result.getSerialNumber());
        assertEquals("CCG-IT-DEPT", result.getLocation());
        assertEquals("R123", result.getRackNumber());

        verify(assetRepository, times(1)).findAllById(assetId);
        verify(modelMapper, times(1)).map(asset, AssetDto.class);
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Get Asset By Id - Asset Not Found")
    public void getAssetById_NotFound() {
        // Arrange
        Long assetId = 1L;
        when(assetRepository.findAllById(assetId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            assetService.getAssetById(assetId);
        });

        assertEquals("Asset doesn't exist with a given Id:1", exception.getMessage());

        verify(assetRepository, times(1)).findAllById(assetId);
        verify(modelMapper, never()).map(any(), eq(AssetDto.class));
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Update Asset Successfully")
    public void testUpdateAsset_Success() {
        // Arrange
        Long assetId = 1L;
        AssetDto updateAssetDto = new AssetDto();
        updateAssetDto.setAssetNumber("A123");
        updateAssetDto.setBrand("BrandX");
        updateAssetDto.setModel("ModelY");
        updateAssetDto.setType("TypeZ");
        updateAssetDto.setSerialNumber("S12345");
        updateAssetDto.setLocation("LocationA");
        updateAssetDto.setRackNumber("R123");

        Asset existingAsset = new Asset();
        existingAsset.setId(assetId);
        existingAsset.setAssetNumber("A111");
        existingAsset.setBrand("OldBrand");
        existingAsset.setModel("OldModel");
        existingAsset.setType("OldType");
        existingAsset.setSerialNumber("OldSerial");
        existingAsset.setLocation("OldLocation");
        existingAsset.setRackNumber("OldRack");

        Asset updatedAsset = new Asset();
        updatedAsset.setId(assetId);
        updatedAsset.setAssetNumber("A123");
        updatedAsset.setBrand("BrandX");
        updatedAsset.setModel("ModelY");
        updatedAsset.setType("TypeZ");
        updatedAsset.setSerialNumber("S12345");
        updatedAsset.setLocation("LocationA");
        updatedAsset.setRackNumber("R123");

        AssetDto expectedAssetDto = new AssetDto();
        expectedAssetDto.setId(assetId);
        expectedAssetDto.setAssetNumber("A123");
        expectedAssetDto.setBrand("BrandX");
        expectedAssetDto.setModel("ModelY");
        expectedAssetDto.setType("TypeZ");
        expectedAssetDto.setSerialNumber("S12345");
        expectedAssetDto.setLocation("LocationA");
        expectedAssetDto.setRackNumber("R123");

        when(assetRepository.findAllById(assetId)).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(existingAsset)).thenReturn(updatedAsset);
        when(modelMapper.map(updatedAsset, AssetDto.class)).thenReturn(expectedAssetDto);

        // Act
        AssetDto result = assetService.updateAsset(assetId, updateAssetDto);

        // Assert
        assertNotNull(result);
        assertEquals(assetId, result.getId());
        assertEquals("A123", result.getAssetNumber());
        assertEquals("BrandX", result.getBrand());
        assertEquals("ModelY", result.getModel());
        assertEquals("TypeZ", result.getType());
        assertEquals("S12345", result.getSerialNumber());
        assertEquals("LocationA", result.getLocation());
        assertEquals("R123", result.getRackNumber());

        verify(assetRepository, times(1)).findAllById(assetId);
        verify(assetRepository, times(1)).save(existingAsset);
        verify(modelMapper, times(1)).map(updatedAsset, AssetDto.class);
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Update Asset - Asset Not Found")
    public void testUpdateAsset_NotFound() {
        // Arrange
        Long assetId = 1L;
        AssetDto updateAssetDto = new AssetDto();
        when(assetRepository.findAllById(assetId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            assetService.updateAsset(assetId, updateAssetDto);
        });

        assertEquals("Asset doesn't exist with a given Id:1", exception.getMessage());

        verify(assetRepository, times(1)).findAllById(assetId);
        verify(assetRepository, never()).save(any());
        verify(modelMapper, never()).map(any(), eq (AssetDto.class));
    }

}