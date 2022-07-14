package com.kabook.kabook.service;
import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.DTO.ProductSearchRequest;
import com.kabook.kabook.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IProductService {
    String createProduct(ProductDTO productDTO);
    Product findById(Long id);
    List<Product> listAllTheProducts();
    //este es el que estamos trabajando, filtrando segun categoria
    List<Product> listProductByCity(Long cityId);
    List<Product> listProductByCategory(Long categoryId);
    List<Product> listProductsDisordened();
	List<Product> filterProductsByDate(List<Product> productsByCityList, ProductSearchRequest productSearchRequest);
	boolean deleteProduct(Long id);
}
