
package com.example.demo.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.StudentRequestDTO;
import com.example.demo.DTO.StudentResponseDTO;

import com.example.demo.Service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    StudentService studentservice; 
    @GetMapping
    public List<StudentResponseDTO> getStudents() {
    
        return studentservice.getAllStudent();
    }   
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getstudentbyid( @PathVariable Long id) {
      StudentResponseDTO s=studentservice.getstudentbyid(id);
        if (s != null) {
            return ResponseEntity.ok(s);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }   
    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudent(
            @RequestBody StudentRequestDTO s) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservice.addStudent(s));
    } 
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updatestudent(@PathVariable Long id,@RequestBody StudentRequestDTO s) {
        StudentResponseDTO updatedStudent=studentservice.updatestudent(id, s);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
       return ResponseEntity.status(HttpStatus.NOT_FOUND) .build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletestudent(@PathVariable Long id) {
      String msg = studentservice.deletestudent(id);
        if (msg.equals("Student Removed Successfully")) {
            return ResponseEntity.ok(msg);
        }
        return ResponseEntity .status(HttpStatus.NOT_FOUND).body(msg);
    }
} 