package mvpbackend.mvp.MainApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    List<Shipment> findByLocationId(Integer locationId);
    List<Shipment> findByLocationWarehouseId(Integer warehouseId);
}
