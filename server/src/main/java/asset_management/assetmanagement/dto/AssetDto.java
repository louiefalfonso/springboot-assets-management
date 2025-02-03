package asset_management.assetmanagement.dto;

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


}
