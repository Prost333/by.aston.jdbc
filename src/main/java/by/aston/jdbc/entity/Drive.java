package by.aston.jdbc.entity;


import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@ToString
@Builder
public class Drive {

    private Long id;

    private BigDecimal km;

    private  BigDecimal time;

    private  BigDecimal surge;

    private  String city;

    private  String rate;

    private BigDecimal paidTime;
    private  Integer doorToDoor;

    private  BigDecimal paidSubmission;

    private  BigDecimal dopSum;

    private  Long userId;

}
