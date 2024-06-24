package mvpbackend.mvp.MainApp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private java.sql.Time time;
    private String location;
    private String reportId;

    @OneToOne
    @JoinColumn(name = "shipment_id")
    @JsonIgnore
    private Shipment shipment;

}
