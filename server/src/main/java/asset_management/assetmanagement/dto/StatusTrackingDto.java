package asset_management.assetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusTrackingDto {

    private Long id;

    private String status;

    private AssetDto asset;

    //Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AssetDto getAsset() {
        return asset;
    }

    public void setAsset(AssetDto asset) {
        this.asset = asset;
    }
}
