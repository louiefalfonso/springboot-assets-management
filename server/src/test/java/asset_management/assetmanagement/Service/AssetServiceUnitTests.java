package asset_management.assetmanagement.Service;

import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.service.impl.AssetServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AssetServiceUnitTests {

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AssetServiceImpl assetService;

    private Asset asset;

    @BeforeEach
    public void setup() {
        asset = Asset.builder()
                .id(1L)
                .assetNumber("C0000001")
                .brand("Lenovo")
                .model("Acer 314 14 Inch Chromebook (CB314-2H)")
                .type("Chromebook")
                .serialNumber("MT8183C")
                .location("CCG-IT-DEPT")
                .rackNumber("MT8183C")
                .build();
    }



    @Test
    public void getAssetById() {
        // Mock the repository method
        given(assetRepository.findById(1L)).willReturn(Optional.of(asset));

        // Call the service method
        Asset result = assetService.getAssetById(asset.getId()).get();

        // Assert that the result is not null and matches the expected asset
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(asset);
    }



}