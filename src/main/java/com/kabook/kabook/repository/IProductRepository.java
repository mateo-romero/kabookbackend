package com.kabook.kabook.repository;

import com.kabook.kabook.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository <Product, Long> {
    //creamos query especifica para listar segun ciudad y categoria
    @Query(value = "SELECT * FROM product p WHERE p.city_id = ?1", nativeQuery = true)
    List<Product> listProductsByCity(@Param("id") Long cityId);

    // segunda query especifica, con categorias.
    @Query(value = "SELECT * FROM product p WHERE p.category_id= ?1", nativeQuery = true)
    List<Product> listProductsByCategory(Long categoryId);

    @Query(value = "SELECT * FROM product p ORDER BY RAND() LIMIT 8", nativeQuery = true)
    List<Product>listProductsDisordered();
    @Query(value = "INSERT INTO product_feature (product_id, feature_id) VALUES (?, ?);", nativeQuery = true)
	int saveProductFeatures(Long productId, Long featureId);
}
