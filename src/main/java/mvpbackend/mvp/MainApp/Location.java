    package mvpbackend.mvp.MainApp;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonManagedReference;

    import java.util.List;

    @Entity
    @Table(name = "locations")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;

        @ManyToOne
        @JoinColumn(name = "warehouse_id")
        @JsonBackReference
        private Warehouse warehouse;

        @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference
        private List<Shipment> shipments;
    }
