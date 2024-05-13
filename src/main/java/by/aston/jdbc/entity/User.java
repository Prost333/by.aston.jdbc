package by.aston.jdbc.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private  Long id;
    private  String name;
    private  String surname;
}
