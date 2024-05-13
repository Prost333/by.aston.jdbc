package by.aston.jdbc.entity;



import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Rates {

    private Long id;

    private String name;

    private String city;

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
