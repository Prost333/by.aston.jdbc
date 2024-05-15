package by.aston.jdbc.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@ToString
@Builder
@Entity
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal km;

    private BigDecimal time;

    private BigDecimal surge;

    private String city;

    private String rate;

    private BigDecimal paidTime;
    private Integer doorToDoor;

    private BigDecimal paidSubmission;

    private BigDecimal dopSum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
