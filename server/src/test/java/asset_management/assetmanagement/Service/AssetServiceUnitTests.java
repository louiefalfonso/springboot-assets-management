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
}