package com.example.demo.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.demo.Model.Product;


@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {


    @Query("""
    SELECT p
    FROM Product p
    WHERE p.active = true
      AND (
           LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
        OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
      )
    """)
List<Product> searchProduct(String keyword);
   
}
