package mvpbackend.mvp.MainApp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "shipments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String shipmentName;
    private String scanId;
    private Time scanTime;
    private String prodStatus;
    private String boundStatus;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonBackReference
    private Location location;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Report report;
}
