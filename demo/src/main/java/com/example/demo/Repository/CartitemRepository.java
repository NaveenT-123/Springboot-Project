package com.example.demo.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Cartitem;
import com.example.demo.Model.Product;
import com.example.demo.Model.Student;


@Repository
public interface CartitemRepository extends JpaRepository<Cartitem,Long>{
@Query("""
        SELECT c
       FROM Cartitem c
       WHERE c.student = :student
       AND c.product = :product
        """)
   
    public Cartitem findByStudentANDProduct(Student student, Product product);
    List<Cartitem> findByStudent(Student student);


   
}



