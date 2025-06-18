package com.neo.ihs.binding;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CitizenAppRegistrationInputs {

    private  String fullName;
    private  String email;
    private String gender;
    private Long phoneNumber;
    private Long ssn;
    private LocalDate dob;
}
