package by.aston.jdbc.entity;



import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Rates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="name", referencedColumnName="name")
    private NameRates name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="city", referencedColumnName="name")
    private City city;

    private BigDecimal landing;

    private BigDecimal door_to_door;

    private BigDecimal price_km_1;

    private BigDecimal price_km_2;

    private BigDecimal minDistance;

    private BigDecimal maxDistance;

    private BigDecimal km_suburb;

    private BigDecimal timeRoad;

    private  BigDecimal minTimeRoad;

    private BigDecimal paidWaiting;

}
