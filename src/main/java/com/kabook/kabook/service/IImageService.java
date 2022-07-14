package com.kabook.kabook.service;

import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.Image;

import java.util.List;
import java.util.Set;

public interface IImageService {
    List<Image> listAllTheImages();
    Image saveImage(Image image);
    boolean saveImagesFromProduct(ProductDTO productDTO);
}
