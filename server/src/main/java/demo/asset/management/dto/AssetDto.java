package demo.asset.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {

    private Long id;
    private String assetNumber;
    private String brand;
    private String model;
    private String type;
    private String serialNumber;
    private String location;
    private String rackNumber;
    private Date created_at;

}
