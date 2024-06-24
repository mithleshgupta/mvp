package mvpbackend.mvp.MainApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Services {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ReportRepository reportRepository;

    public Warehouse createWarehouse(String name, List<String> locationNames) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);

        List<Location> locations = locationNames.stream()
                .map(locationName -> {
                    Location location = new Location();
                    location.setName(locationName);
                    location.setWarehouse(warehouse);
                    return location;
                }).collect(Collectors.toList());

        warehouse.setLocations(locations);
        return warehouseRepository.save(warehouse);
    }

    public List<Shipment> getShipmentsByWarehouseId(Integer warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse ID"));
        List<Location> locations = warehouse.getLocations();
        List<Shipment> shipments = new ArrayList<>();
        for (Location location : locations) {
            shipments.addAll(shipmentRepository.findByLocationId(location.getId()));
        }
        return shipments;
    }

    public List<Shipment> getShipmentsByLocationId(Integer locationId) {
        return shipmentRepository.findByLocationId(locationId);
    }

    public boolean createShipment(String shipmentName, String scanId, String scanTimeStr, String prodStatus, Integer locationId) {
        Shipment shipment = new Shipment();
        shipment.setShipmentName(shipmentName);
        shipment.setScanId(scanId);
        shipment.setProdStatus(prodStatus);


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            Time scanTime = new Time(sdf.parse(scanTimeStr).getTime());
            shipment.setScanTime(scanTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format for scanTime. Expected format: HH:mm:ss");
        }

        Location location = locationRepository.findById(locationId).orElseThrow(() -> new IllegalArgumentException("Invalid location ID"));
        shipment.setLocation(location);
        shipmentRepository.save(shipment);
        return true;
    }

    public boolean createReport(String name, String time, String location, String reportId, Integer shipmentId) {
        Report report = new Report();
        report.setName(name);
        report.setLocation(location);
        report.setReportId(reportId);


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            long ms = sdf.parse(time).getTime();
            Time sqlTime = new Time(ms);
            report.setTime(sqlTime);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid time format");
        }

        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shipment ID"));
        report.setShipment(shipment);
        reportRepository.save(report);
        return true;
    }

    public Report getReportByShipmentId(Integer shipmentId) {
        return reportRepository.findById(shipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid shipment ID"));
    }

    public List<ShipmentStatusCountDTO> getShipmentStatusCountByWarehouse(Integer warehouseId) {
        List<Shipment> shipments = shipmentRepository.findByLocationWarehouseId(warehouseId);

        Map<String, Long> statusCounts = shipments.stream()
                .collect(Collectors.groupingBy(Shipment::getProdStatus, Collectors.counting()));

        return statusCounts.entrySet().stream()
                .map(entry -> new ShipmentStatusCountDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public BoundStatusCountDTO getBoundStatusCountByWarehouse(Integer warehouseId) {
        List<Shipment> shipments = shipmentRepository.findByLocationWarehouseId(warehouseId);

        long inboundCount = shipments.stream()
                .filter(shipment -> "Inbound".equals(shipment.getBoundStatus()))
                .count();

        long outboundCount = shipments.stream()
                .filter(shipment -> "Outbound".equals(shipment.getBoundStatus()))
                .count();

        return new BoundStatusCountDTO(inboundCount, outboundCount);
    }

}
