package com.example.demo.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import com.example.demo.Service.ProductService;
import com.example.demo.DTO.ProductRequestDTO;
import com.example.demo.DTO.ProductResponseDTO;


@RestController
@RequestMapping("/api/v1/products")
public class ProductContoller {
    @Autowired
    ProductService productservice;
    @PostMapping
    public ResponseEntity<ProductResponseDTO> addproduct(@RequestBody ProductRequestDTO p){
       return ResponseEntity.status(HttpStatus.CREATED).body(productservice.addproduct(p));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getallproducts(){
        return ResponseEntity.ok(productservice.getallproducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getproductbyid(@PathVariable Long id){
        return ResponseEntity.ok(productservice.getproductbyid(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@RequestBody ProductRequestDTO  p){
        ProductResponseDTO  updatedproduct=productservice.updateproduct(id,p);
        if(updatedproduct!=null){
            return ResponseEntity.ok(updatedproduct);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND) .build();
        }


   
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteproduct(@PathVariable Long id){
     String msg = productservice.deleteproduct(id);
        return ResponseEntity.ok(msg);
    }
    @GetMapping("/search")
        public ResponseEntity<List<ProductResponseDTO>> searchproduct(@RequestParam String keyword){
            return ResponseEntity.ok(productservice.searchProduct(keyword));
    }
   
    }





