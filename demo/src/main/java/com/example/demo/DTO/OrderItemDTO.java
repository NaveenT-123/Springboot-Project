package com.example.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.*;
@Data
@AllArgsConstructor
public class OrderItemDTO {
    private Long Id;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
