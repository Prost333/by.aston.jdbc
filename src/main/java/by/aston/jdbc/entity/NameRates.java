package by.aston.jdbc.entity;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@ToString
@Builder
public class NameRates {
    private  Long id;
    private  String name;
}
