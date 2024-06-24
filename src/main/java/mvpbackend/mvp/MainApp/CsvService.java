package mvpbackend.mvp.MainApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public static void main(String[] args) {
        System.out.println("hello");
    }
//    private Repository repository;
//
//    public void processCsvFile(MultipartFile file) throws IOException {
//        List<Shipments> shipmentsList = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
//            String line;
//            boolean isFirstLine = true;
//            while ((line = br.readLine()) != null) {
//                if (isFirstLine) {
//                    isFirstLine = false;
//                    continue;
//                }
//                String[] fields = line.split(",");
//                Shipments shipment = new Shipments();
//                shipment.setShipmentName(fields[0]);
//                shipment.setScanId(fields[1]);
//                shipment.setScanTime(Time.valueOf(fields[2]));
//                shipment.setLocation(fields[3]);
//                shipment.setProdStatus(fields[4]);
//                shipmentsList.add(shipment);
//            }
//        }
//
//        for (Shipments shipment : shipmentsList) {
//            repository.saveShipment(shipment);
//        }
    }

