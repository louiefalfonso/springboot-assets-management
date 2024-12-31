package asset_management.assetmanagement.Service;

import asset_management.assetmanagement.dto.AssetDto;
import asset_management.assetmanagement.entity.Asset;
import asset_management.assetmanagement.repository.AssetRepository;
import asset_management.assetmanagement.service.impl.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AssetServiceUnitTests {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImpl assetService;

    private Asset asset;


    @BeforeEach
    public void setup(){
        Asset asset = Asset.builder()
                .assetNumber("C0000001")
                .brand("Lenovo")
                .model("Acer 314 14 Inch Chromebook (CB314-2H)")
                .type("Chromebook")
                .serialNumber("MT8183C")
                .location("CCG-IT-DEPT")
                .rackNumber("MT8183C")
                .build();
    }

}
