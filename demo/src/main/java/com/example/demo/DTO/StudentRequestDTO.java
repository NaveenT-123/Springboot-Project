package com.example.demo.DTO;
import lombok.Data;

@Data
public class StudentRequestDTO {

    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private AddressDTO address;

    
}