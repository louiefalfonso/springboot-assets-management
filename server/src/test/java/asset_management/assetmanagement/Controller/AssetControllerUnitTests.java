package asset_management.assetmanagement.Controller;

import asset_management.assetmanagement.controller.AssetController;
import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.service.AssetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AssetControllerUnitTests {

    @Mock
    private AssetService assetService;

    @InjectMocks
    private AssetController assetController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
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
    void createAsset_ServiceThrowsException() {
        // Arrange
        AssetDto inputAssetDto = new AssetDto();
        inputAssetDto.setAssetNumber("12345");

        when(assetService.createNewAsset(inputAssetDto)).thenThrow(new RuntimeException("Service Error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            assetController.createAsset(inputAssetDto);
        });

        assertEquals("Service Error", exception.getMessage());
        verify(assetService, times(1)).createNewAsset(inputAssetDto);
    }
}
