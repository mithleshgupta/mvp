package mvpbackend.mvp.MainApp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WarehouseRequest {
    private String name;
    private List<String> locationNames;
}
