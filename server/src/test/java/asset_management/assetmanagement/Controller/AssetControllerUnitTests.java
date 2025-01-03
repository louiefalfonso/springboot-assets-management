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

        // Verify
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
        assertNull(response.getBody());

        // Verify
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
        Exception exception = assertThrows(RuntimeException.class, () -> assetController.createAsset(inputAssetDto));

        assertEquals("Service Error", exception.getMessage());

        // Verify
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

    @Test
    @Order(6)
    @DisplayName("Test 6: Get Asset By Id - When Asset Does Not Exist")
    public void getAssetById_WhenAssetDoesNotExist() {
        // Arrange
        Long assetId = 999L; // Non-existent asset ID

        // Mock the behavior of assetRepository.findAllById() to return an empty Optional
        when(assetRepository.findAllById(assetId)).thenReturn(Optional.empty());

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> assetController.getAssetById(assetId));

        // Verify the exception message
        assertEquals("Asset does not exist with Id:999", exception.getMessage());
    }

    @Test
    @Order(7)
    @DisplayName("Test 7: Update Assets Success")
    void updateAsset_Success() {
        // Arrange
        long assetId = 1L;
        Asset existingAsset = new Asset();
        existingAsset.setId(assetId);
        existingAsset.setAssetNumber("A123");
        existingAsset.setBrand("BrandA");
        existingAsset.setModel("ModelX");
        existingAsset.setType("Type1");
        existingAsset.setSerialNumber("SN123");
        existingAsset.setLocation("LocationA");
        existingAsset.setRackNumber("Rack1");

        Asset updatedAssetDetails = new Asset();
        updatedAssetDetails.setAssetNumber("A456");
        updatedAssetDetails.setBrand("BrandB");
        updatedAssetDetails.setModel("ModelY");
        updatedAssetDetails.setType("Type2");
        updatedAssetDetails.setSerialNumber("SN456");
        updatedAssetDetails.setLocation("LocationB");
        updatedAssetDetails.setRackNumber("Rack2");

        when(assetRepository.findById(assetId)).thenReturn(Optional.of(existingAsset));
        when(assetRepository.save(existingAsset)).thenReturn(existingAsset);

        // Act
        ResponseEntity<Asset> response = assetController.updateAsset(assetId, updatedAssetDetails);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("A456", response.getBody().getAssetNumber());
        assertEquals("BrandB", response.getBody().getBrand());
        assertEquals("ModelY", response.getBody().getModel());
        assertEquals("Type2", response.getBody().getType());
        assertEquals("SN456", response.getBody().getSerialNumber());
        assertEquals("LocationB", response.getBody().getLocation());
        assertEquals("Rack2", response.getBody().getRackNumber());

        // Verify
        verify(assetRepository, times(1)).findById(assetId);
        verify(assetRepository, times(1)).save(existingAsset);
    }

    @Test
    @Order(8)
    @DisplayName("Test 8: Update Asset - Asset Not Found")
    void updateAsset_AssetNotFound() {
        // Arrange
        long assetId = 1L;
        Asset updatedAssetDetails = new Asset();

        when(assetRepository.findById(assetId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> assetController.updateAsset(assetId, updatedAssetDetails));

        // Assert
        assertEquals("Asset does not exist with id: " + assetId, exception.getMessage());

        // Verify
        verify(assetRepository, times(1)).findById(assetId);
        verify(assetRepository, never()).save(any());
    }

    @Test
    @Order(9)
    @DisplayName("Test 9: Delete Asset Success")
    void deleteAsset_Success() {
        // Arrange
        Long assetId = 1L;

        // Mock the service method to do nothing (since it's a void method)
        doNothing().when(assetService).deleteAsset(assetId);

        // Act
        ResponseEntity<String> response = assetController.deleteAsset(assetId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Asset Deleted Successfully", response.getBody());

        // Verify that the service method was called once
        verify(assetService, times(1)).deleteAsset(assetId);
    }

    @Test
    @Order(10)
    @DisplayName("Test 10: Delete Asset - Exception Thrown")
    void deleteAsset_ExceptionThrown() {
        // Arrange
        Long assetId = 1L;

        // Mock the service method to throw an exception
        doThrow(new RuntimeException("Asset not found")).when(assetService).deleteAsset(assetId);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> assetController.deleteAsset(assetId));

        assertEquals("Asset not found", exception.getMessage());

        // Verify that the service method was called once
        verify(assetService, times(1)).deleteAsset(assetId);
    }
}
