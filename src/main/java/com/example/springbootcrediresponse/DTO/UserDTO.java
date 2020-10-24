package com.example.springbootcrediresponse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Id
    private String id;
    private String name;
    private String surname;
    private String identificationNumber;
    private String phoneNumber;
    private double mounthlyIncome;
    private double credit;
}
