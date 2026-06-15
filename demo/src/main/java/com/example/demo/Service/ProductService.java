package com.example.demo.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.example.demo.DTO.ProductRequestDTO;


import com.example.demo.DTO.ProductResponseDTO;
import com.example.demo.Model.Product;


import com.example.demo.Repository.ProductRepository;




@Service
public class ProductService {
    @Autowired
    private ProductRepository productrepository;
    public ProductResponseDTO addproduct(ProductRequestDTO productrequest){
        Product product=new Product();
        updateProductFromRequest(product,productrequest);
        Product savedproduct=productrepository.save(product);
        return mapToProductResponseDTO(savedproduct);
    }
    public List<ProductResponseDTO> getallproducts(){
        return productrepository.findAll().stream().map(this::mapToProductResponseDTO).toList();
    }
    public ProductResponseDTO getproductbyid(Long id){
        Product product = productrepository.findById(id)
        .orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "THE PRODUCT WITH ID " + id + " NOT FOUND"
            )
        );
        return mapToProductResponseDTO(product);
    }
    public ProductResponseDTO updateproduct(Long id, ProductRequestDTO productrequest) {
            Product product = productrepository.findById(id).orElse(null);


        if (product == null) {
            return null;
        }
   
         else{
            updateProductFromRequest(product,productrequest);
            product = productrepository.save(product);
            return mapToProductResponseDTO(product);
         }




    }
    /*sir method
    public ProductResponseDTO updateProduct(
        Long id,
        ProductRequestDto productRequest) {


    return productRepository.findById(id)
            .map(existingProduct -> {


                updateProductFromRequest(
                        existingProduct,
                        productRequest
                );


                Product savedProduct =
                        productRepository.save(existingProduct);


                return mapToProductResponseDTO(savedProduct);
            })
            .orElseThrow(() ->
                    new RuntimeException(
                            "Product not found with id: " + id
                    ));
}
   
    */


public String deleteproduct(Long id){
    Product product = productrepository.findById(id)
        .orElseThrow(() ->
            new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "THE PRODUCT WITH ID " + id + " NOT FOUND"
            )
        );
    product.setActive(false);
    productrepository.save(product);
    return "Product deactivated";
}


public List<ProductResponseDTO>searchProduct(String keyword) {
 return productrepository
            .searchProduct(keyword)
            .stream()
            .map(this::mapToProductResponseDTO)
            .toList();
}




    public void updateProductFromRequest(Product product,ProductRequestDTO productrequest){
        product.setName(productrequest.getName());
        product.setDescription(productrequest.getDescription());
        product.setPrice(productrequest.getPrice());
        product.setStockQuantity(productrequest.getStockQuantity());
        product.setCategory(productrequest.getCategory());
        product.setImageUrl(productrequest.getImageUrl());
        product.setActive(true);




    }
    public ProductResponseDTO mapToProductResponseDTO(Product saveProduct){
        ProductResponseDTO productResponse=new ProductResponseDTO();
        productResponse.setId(saveProduct.getId());
        productResponse.setName(saveProduct.getName());
        productResponse.setDescription(saveProduct.getDescription());
        productResponse.setPrice(saveProduct.getPrice());
        productResponse.setStockQuantity(saveProduct.getStockQuantity());
        productResponse.setCategory(saveProduct.getCategory());
        productResponse.setImageUrl(saveProduct.getImageUrl());
        return productResponse;


    }  
}    