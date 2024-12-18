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
    private String errorMessage;

    public String getAssetNumber() {
        return assetNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getRackNumber() {
        return rackNumber;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
