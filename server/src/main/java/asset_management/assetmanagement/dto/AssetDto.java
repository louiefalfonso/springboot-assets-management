package asset_management.assetmanagement.dto;

import asset_management.assetmanagement.entity.Asset;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetDto {

    private Long id;
    private String assetNumber;
    private String brand;
    private String model;
    private String type;
    private String serialNumber;
    private String location;
    private String rackNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date dateCreated;

    private List<StatusHistoryDto> statusHistory;
    private List<StatusTrackingDto> statusTracking;
    private List<WarrantyStatusDto> warrantyStatus;

    //Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(String rackNumber) {
        this.rackNumber = rackNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<StatusHistoryDto> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<StatusHistoryDto> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public List<StatusTrackingDto> getStatusTracking() {
        return statusTracking;
    }

    public void setStatusTracking(List<StatusTrackingDto> statusTracking) {
        this.statusTracking = statusTracking;
    }

    public List<WarrantyStatusDto> getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(List<WarrantyStatusDto> warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }


    public Asset get() {
        return null;
    }
}
