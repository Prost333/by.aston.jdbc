package by.aston.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriveResp {

    private BigDecimal km;

    private  BigDecimal time;

    private  BigDecimal surge;

    private  String city;

    private  String rate;

    private BigDecimal paidTime;
    private  Integer doorToDoor;

    private  BigDecimal paidSubmission;

    private  BigDecimal dopSum;

    private  String userName;
}
