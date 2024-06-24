package mvpbackend.mvp.MainApp;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class ReportRequest {
    private String name;
    private String time;
    private String location;
    private String reportId;
    private Integer shipmentId;


}

