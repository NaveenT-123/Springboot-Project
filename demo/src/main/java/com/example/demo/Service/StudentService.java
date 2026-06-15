package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AddressDTO;

import com.example.demo.DTO.StudentRequestDTO;
import com.example.demo.DTO.StudentResponseDTO;
import com.example.demo.Model.Address;
import com.example.demo.Model.Student;
import com.example.demo.Model.UserRole;
import com.example.demo.Repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<StudentResponseDTO> getAllStudent() {
        return repo.findAll()
                   .stream()
                   .map(this::mapToStudentResponse)
                   .toList();
    }

    public StudentResponseDTO getstudentbyid(Long id) {
        return repo.findById(id)
                   .map(this::mapToStudentResponse)
                   .orElse(null);
    }
    
    

    public StudentResponseDTO addStudent(StudentRequestDTO s) {

        Address address = new Address();
        address.setStreet(s.getAddress().getStreet());
        address.setCity(s.getAddress().getCity());
        address.setState(s.getAddress().getState());
        address.setCountry(s.getAddress().getCountry());
        address.setZipcode(s.getAddress().getZipcode());

        Student student = new Student();
        student.setFirstname(s.getFirstname());
        student.setLastname(s.getLastname());
        student.setPhone(s.getPhone());
        student.setEmail(s.getEmail());
        student.setPassword(s.getPassword());
        student.setUserRole(UserRole.Student);
        student.setAddress(address);

        return mapToStudentResponse(repo.save(student));
    }

    public StudentResponseDTO updatestudent(Long id,
                                            StudentRequestDTO s) {

        Student student = repo.findById(id).orElse(null);

        if (student == null) {
            return null;
        }

        student.setFirstname(s.getFirstname());
        student.setLastname(s.getLastname());
        student.setPhone(s.getPhone());
        student.setEmail(s.getEmail());
        student.setPassword(s.getPassword());

        if (s.getAddress() != null) {

            Address address = student.getAddress();
            
            address.setStreet(s.getAddress().getStreet());
            address.setCity(s.getAddress().getCity());
            address.setState(s.getAddress().getState());
            address.setCountry(s.getAddress().getCountry());
            address.setZipcode(s.getAddress().getZipcode());
        }

        return mapToStudentResponse(repo.save(student));
    }

    public String deletestudent(Long id) {

        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Student Removed Successfully";
        }

        return "Student Not Found";
    }

    private StudentResponseDTO mapToStudentResponse(Student student) {
         StudentResponseDTO response = new StudentResponseDTO();
        response.setId(student.getId());
        response.setFirstname(student.getFirstname());
        response.setLastname(student.getLastname());
        response.setPhone(student.getPhone());
        response.setEmail(student.getEmail());
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(student.getAddress().getId());
        addressDTO.setStreet(student.getAddress().getStreet());
        addressDTO.setCity(student.getAddress().getCity());
        addressDTO.setState(student.getAddress().getState());
        addressDTO.setCountry(student.getAddress().getCountry());
        addressDTO.setZipcode(student.getAddress().getZipcode());

        
        
        response.setAddress(addressDTO);

        return response;
    }
}