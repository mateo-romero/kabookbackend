package com.kabook.kabook.service.Impl;

import com.kabook.kabook.model.DTO.ProductDTO;
import com.kabook.kabook.model.Image;
import com.kabook.kabook.repository.IImageRepository;
import com.kabook.kabook.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements IImageService {
    @Autowired
    private IImageRepository iImageRepository;

    @Override
    public List<Image> listAllTheImages() {
        return iImageRepository.findAll();
    }

    @Override
    public Image saveImage(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public boolean saveImagesFromProduct(ProductDTO productDTO) {
        Set<Image> imageSet = productDTO.getImages();
        if (imageSet != null && !imageSet.isEmpty()) {
            for (Image image: imageSet) {
                image.setId(productDTO.getId());
                Long idNewImage = saveImage(image).getId();
                if (idNewImage == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
