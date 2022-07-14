package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.*;
import com.kabook.kabook.model.DTO.BookingDTO;
import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.DTO.ProductSearchRequest;
import com.kabook.kabook.repository.ICategoryRepository;
import com.kabook.kabook.repository.ICityRepository;
import com.kabook.kabook.repository.IProductRepository;
import com.kabook.kabook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {


	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private ICityRepository cityRepository;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private IBookingService bookingService;

	@Autowired
	private IFeatureService featureService;

	@Autowired
	private IImageService imageService;

	@Autowired
	private IPolicyService policyService;

	@Override
	public String createProduct(ProductDTO productDTO) {
		boolean areFeaturesSavedSuccessfully = featureService.saveFeaturesFromProduct(productDTO);
		Product productToSave = prepareProductToSave(productDTO);
		if (areFeaturesSavedSuccessfully && productToSave != null) {
			Product product = productRepository.save(productToSave);
			boolean areImagesSaved = saveImages(productDTO.getImages(), product);
			boolean arePoliciesSaved = savePolicies(productDTO.getPolicies(), product);
			if (areImagesSaved && arePoliciesSaved) {
				return "Producto agregado con exito!";
			}
		}
		return "Algo salio mal guardando el producto, por favor intente nuevamente.";
	}

	private boolean savePolicies(Set<Policy> policies, Product product) {
		policies.forEach(policy -> policy.setProduct(product));
		Set<Policy> savedImages = policies.stream().map(policy -> policyService.save(policy)).collect(Collectors.toSet());
		return !savedImages.isEmpty();
	}

	private boolean saveImages(Set<Image> images, Product product) {
		images.forEach(image -> image.setProduct(product));
		Set<Image> savedImages = images.stream().map(image -> imageService.saveImage(image)).collect(Collectors.toSet());
		return !savedImages.isEmpty();
	}

	private Product prepareProductToSave(ProductDTO productDTO) {
		Category category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
		City city = cityRepository.findById(productDTO.getCityId()).orElse(null);
		Set<Feature> features = getAllFeatures(productDTO);
		if (category != null && city != null) {
			return new Product(
					productDTO.getName(),
					productDTO.getDescription(),
					productDTO.getLocation(),
					productDTO.getLatitude(),
					productDTO.getLongitude(),
					productDTO.getRating(),
					productDTO.getIsActive(),
					category,
					city,
					features
			);
		}
		return null;
	}

	@Override
	public Product findById(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		return productOptional.isPresent() && productOptional.get().getIsActive()
				? productOptional.get()
				: null;
	}

	public boolean deleteProduct(Long id){
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			product.setIsActive(false);
			productRepository.save(product);
			return true;
		}
		return false;
	};

	@Override
	public List<Product> listAllTheProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> listProductByCity(Long cityId) {
		return productRepository.listProductsByCity(cityId);
	}

	@Override
	public List<Product> listProductByCategory(Long categoryId) {
		return productRepository.listProductsByCategory(categoryId);
	}

	@Override
	public List<Product> listProductsDisordened() {
		return productRepository.listProductsDisordered();
	}

	@Override
	public List<Product> filterProductsByDate(List<Product> productsByCityList, ProductSearchRequest productSearchRequest) {
		LocalDate dateBookingCheckIn = productSearchRequest.getDateBookingCheckIn();
		LocalDate dateBookingCheckOut = productSearchRequest.getDateBookingCheckOut();
		List<BookingDTO> bookingDTOList;
		List<Product> filteredProductsList = new ArrayList<>();
		for (Product product : productsByCityList) {
			bookingDTOList = bookingService.getBookingsByProductId(product.getId());
			if (bookingService.isBookingDateValid(dateBookingCheckIn, dateBookingCheckOut, bookingDTOList)) {
					filteredProductsList.add(product);
			}
		}
		return filteredProductsList;
	}

	public Set<Feature> getAllFeatures(ProductDTO productDTO) {
		Set<Long> featuresIds = productDTO.getFeaturesIds();
		Set<Feature> features = productDTO.getFeatures();
		if(featuresIds != null && !featuresIds.isEmpty()) {
			for (Long featureId : featuresIds) {
				Optional<Feature> featureOptional = featureService.findById(featureId);
				featureOptional.ifPresent(feature -> features.add(feature));
			}
		}
		return features;
	}
}
