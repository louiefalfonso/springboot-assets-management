package asset_management.assetmanagement.Controller;

import asset_management.assetmanagement.controller.AssetController;
import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.service.AssetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AssetControllerUnitTests {

    @Mock
    private AssetService assetService;

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetController assetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Create New Asset Success")
    void createAsset_Success() {
        // Arrange
        AssetDto inputAssetDto = new AssetDto();
        inputAssetDto.setAssetNumber("C0000001");
        inputAssetDto.setBrand("ACER");
        inputAssetDto.setModel("Acer 314 14 Inch Chromebook (CB314-2H)");
        inputAssetDto.setType("Chromebook");
        inputAssetDto.setSerialNumber("MT8183C");
        inputAssetDto.setLocation("CCG-IT-DEPT");
        inputAssetDto.setRackNumber("R123");

        AssetDto savedAssetDto = new AssetDto();
        savedAssetDto.setAssetNumber("C0000001");
        savedAssetDto.setBrand("ACER");
        savedAssetDto.setModel("Acer 314 14 Inch Chromebook (CB314-2H)");
        savedAssetDto.setType("Chromebook");
        savedAssetDto.setSerialNumber("MT8183C");
        savedAssetDto.setLocation("CCG-IT-DEPT");
        savedAssetDto.setRackNumber("R123");

        when(assetService.createNewAsset(inputAssetDto)).thenReturn(savedAssetDto);

        // Act
        ResponseEntity<AssetDto> response = assetController.createAsset(inputAssetDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedAssetDto, response.getBody());
        verify(assetService, times(1)).createNewAsset(inputAssetDto);
    }


    @Test
    @Order(2)
    @DisplayName("Test 2: Create New Asset - Null Input")
    void createAsset_NullInput() {
        // Arrange
        when(assetService.createNewAsset(null)).thenReturn(null);

        // Act
        ResponseEntity<AssetDto> response = assetController.createAsset(null);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(assetService, times(1)).createNewAsset(null);
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Create New Asset - Service Throws Exception")
    void createAsset_ServiceThrowsException() {
        // Arrange
        AssetDto inputAssetDto = new AssetDto();
        inputAssetDto.setAssetNumber("C0000001");

        when(assetService.createNewAsset(inputAssetDto)).thenThrow(new RuntimeException("Service Error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            assetController.createAsset(inputAssetDto);
        });

        assertEquals("Service Error", exception.getMessage());
        verify(assetService, times(1)).createNewAsset(inputAssetDto);
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Get All Assets")
    public void getAllAssets() {
        // Arrange
        AssetDto asset1 = new AssetDto();
        asset1.setId(1L);
        asset1.setAssetNumber("C0000001");
        asset1.setBrand("ACER");

        AssetDto asset2 = new AssetDto();
        asset2.setId(2L);
        asset2.setAssetNumber("C0000001");
        asset2.setBrand("LENOVO");

        List<AssetDto> mockAssets = Arrays.asList(asset1, asset2);

        // Mock the behavior of assetService.getAllAssets()
        when(assetService.getAllAssets()).thenReturn(mockAssets);

        // Act
        ResponseEntity<List<AssetDto>> response = assetController.getAllAssets();

        // Assert
        assertEquals(200, response.getStatusCodeValue()); // Verify status code is 200 OK
        assertEquals(mockAssets, response.getBody()); // Verify the response body matches the mock data
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Get Asset By Id - When Asset Exists")
    public void getAssetById_WhenAssetExists() {
        // Arrange
        Long assetId = 1L;
        Asset mockAsset = new Asset();
        mockAsset.setId(assetId);
        mockAsset.setAssetNumber("C0000001");
        mockAsset.setBrand("ACER");

        // Mock the behavior of assetRepository.findAllById()
        when(assetRepository.findAllById(assetId)).thenReturn(Optional.of(mockAsset));

        // Act
        ResponseEntity<Asset> response = assetController.getAssetById(assetId);

        // Assert
        assertEquals(200, response.getStatusCodeValue()); // Verify status code is 200 OK
        assertNotNull(response.getBody()); // Verify response body is not null
        assertEquals(mockAsset, response.getBody()); // Verify the response body matches the mock asset
    }
}
