package com.ecommerce.productcatalog.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
