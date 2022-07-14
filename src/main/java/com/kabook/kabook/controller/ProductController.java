package com.kabook.kabook.controller;

import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.DTO.ProductSearchRequest;
import com.kabook.kabook.model.Product;
import com.kabook.kabook.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;

	@PostMapping("/save")
	public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
		String message = productService.createProduct(productDTO);
		return ResponseEntity.ok(message);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id)
				? new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.OK)
				: new ResponseEntity<>("No se encuentra el producto", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		Product product = productService.findById(id);
		if (product != null) {
			return ResponseEntity.ok(product);
		}
		return new ResponseEntity<>("Producto no encontrado.", HttpStatus.NOT_FOUND);
	}


	@GetMapping
	public ResponseEntity<List<Product>> listAllTheProducts() {
		return ResponseEntity.ok(productService.listAllTheProducts());
	}

	@GetMapping("/city/{cityId}")
	public ResponseEntity<List<Product>> listProductsByCity(@PathVariable Long cityId) {
		List<Product> productsByCity = productService.listProductByCity(cityId);
		return ResponseEntity.ok(productsByCity);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long categoryId) {
		List<Product> products = productService.listProductByCategory(categoryId);
		if (products != null) {
			return ResponseEntity.ok(products);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}


	@GetMapping("/productsDisordered")
	public ResponseEntity<List<Product>> listProductsDisordered() {
		List<Product> productsDisordered = productService.listProductsDisordened();
		return ResponseEntity.ok(productsDisordered);
	}

	@PostMapping("/by-city-and-date")
	public ResponseEntity<List<Product>> getProductsByCityAndDate(@RequestBody ProductSearchRequest productSearchRequest) {
		List<Product> productsByCityList = productService.listProductByCity(productSearchRequest.getCityId());
		List<Product> filteredProducts = productService.filterProductsByDate(productsByCityList, productSearchRequest);
		return filteredProducts == null || filteredProducts.isEmpty()
				? ResponseEntity.notFound().build()
				: ResponseEntity.ok(filteredProducts);
	}
}
