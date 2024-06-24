package mvpbackend.mvp.MainApp;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class ShipmentRequest {
    private String shipmentName;
    private String scanId;
    private String scanTime;
    private String prodStatus;
    private Integer locationId;
    private String boundStatus;


}

