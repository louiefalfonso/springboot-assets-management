package demo.asset.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assetNumber")
    private String assetNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "serialNumber")
    private String serialNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "rackNumber")
    private String rackNumber;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date created_at;

    public Asset(
            Long id,
            String assetNumber,
            String brand,
            String type,
            String serialNumber,
            String location,
            String model,
            String rackNumber) {
    }
}
