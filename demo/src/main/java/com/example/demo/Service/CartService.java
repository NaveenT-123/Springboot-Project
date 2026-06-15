package com.example.demo.Service;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.CartitemRequestDTO;
import com.example.demo.Model.Cartitem;
import com.example.demo.Model.Product;
import com.example.demo.Model.Student;
import com.example.demo.Repository.CartitemRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.StudentRepository;
import java.util.List;
@Service
public class CartService {
    @Autowired
    private ProductRepository productrepository;
    @Autowired
    private CartitemRepository cartitemrepository;
    @Autowired
    private StudentRepository studentrepository;
    public Boolean addToCart(String studentId,CartitemRequestDTO request){
        Product product;
        Optional<Product> productopt=productrepository.findById(request.getProductId());
        if(productopt.isEmpty()){
            return false;
        }
    else{
        product=productopt.get();
    }
    if(product.getStockQuantity()<request.getQuantity()){
       return false;
    }else{


    Optional<Student> studentopt=studentrepository.findById(Long.valueOf(studentId));
    if(studentopt.isEmpty()){
        return false;
    }else{
        Student student=studentopt.get();
        Cartitem existingCartitem=cartitemrepository.findByStudentANDProduct(student,product);
        if(existingCartitem!=null){
           existingCartitem.setQuantity(existingCartitem.getQuantity()+request.getQuantity());
           existingCartitem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartitem.getQuantity())));
           cartitemrepository.save(existingCartitem);
        }else{
          //create new cart item
          Cartitem cartitem=new Cartitem();
          cartitem.setStudent(student);
          cartitem.setProduct(product);
          cartitem.setQuantity(request.getQuantity());
          cartitem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
           cartitemrepository.save(cartitem);
        }
        return true;
            }
        }
    }

    public boolean deleteFromCart(String studentId,Long productId){
        Optional<Product> productopt=productrepository.findById(productId);
        Optional<Student> studentopt=studentrepository.findById(Long.valueOf(studentId));
        if(productopt.isPresent()&&studentopt.isPresent()){
           Cartitem cartitem=cartitemrepository.findByStudentANDProduct(studentopt.get(),productopt.get());
           if(cartitem!=null){
               cartitemrepository.delete(cartitem);
                return true;
           }
        }
        return false;
    }
    public List<Cartitem> getCartItems(String studentId){
        Optional<Student> studentopt=studentrepository.findById(Long.valueOf(studentId));
        return studentrepository.findById(Long.valueOf(studentId)).map(student -> cartitemrepository.findByStudent(student)).orElse(Collections.emptyList());
    }
}

