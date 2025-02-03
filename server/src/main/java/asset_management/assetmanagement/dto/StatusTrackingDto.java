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

    private Long assetId;
}
