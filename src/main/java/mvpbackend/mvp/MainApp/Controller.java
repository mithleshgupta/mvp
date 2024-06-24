package mvpbackend.mvp.MainApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private Services services;

    @PostMapping("/warehouses")
    public String createWarehouse(@RequestBody WarehouseRequest warehouseRequest) {
        try {
            services.createWarehouse(warehouseRequest.getName(), warehouseRequest.getLocationNames());
            return "Warehouse Created";
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }

    @GetMapping("/warehouses/{warehouseId}/shipments")
    public List<Shipment> getShipmentsByWarehouse(@PathVariable Integer warehouseId) {
        return services.getShipmentsByWarehouseId(warehouseId);
    }

    @GetMapping("/locations/{locationId}/shipments")
    public List<Shipment> getShipmentsByLocation(@PathVariable Integer locationId) {
        return services.getShipmentsByLocationId(locationId);
    }

    @PostMapping("/shipments")
    public String createShipment(@RequestBody ShipmentRequest shipmentRequest) {
        try {
            services.createShipment(
                    shipmentRequest.getShipmentName(),
                    shipmentRequest.getScanId(),
                    String.valueOf(Time.valueOf(shipmentRequest.getScanTime())),
                    shipmentRequest.getProdStatus(),
                    shipmentRequest.getLocationId()
            );
            return "Shipment Created";
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }

    @PostMapping("/reports")
    public String createReport(@RequestBody ReportRequest reportRequest) {
        try {
            services.createReport(
                    reportRequest.getName(),
                    reportRequest.getTime(),
                    reportRequest.getLocation(),
                    reportRequest.getReportId(),
                    reportRequest.getShipmentId()
            );
            return "Report Created";
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }

    @GetMapping("/reports/{shipmentId}")
    public Report getReportByShipmentId(@PathVariable Integer shipmentId) {
        return services.getReportByShipmentId(shipmentId);
    }

    @GetMapping("/{warehouseId}/shipment-status-counts")
    public List<ShipmentStatusCountDTO> getShipmentStatusCounts(@PathVariable Integer warehouseId) {
        return services.getShipmentStatusCountByWarehouse(warehouseId);
    }

    @GetMapping("/warehouses/{warehouseId}/bound-status-counts")
    public BoundStatusCountDTO getBoundStatusCounts(@PathVariable Integer warehouseId) {
        return services.getBoundStatusCountByWarehouse(warehouseId);
    }

}
