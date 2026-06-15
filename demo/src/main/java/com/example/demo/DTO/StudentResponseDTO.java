package com.example.demo.DTO;
import lombok.Data;
@Data
public class StudentResponseDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private AddressDTO address;

    
}