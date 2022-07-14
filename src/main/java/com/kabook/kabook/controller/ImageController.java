package com.kabook.kabook.controller;

import com.kabook.kabook.model.Image;
import com.kabook.kabook.service.Impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {
    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @PostMapping
    public ResponseEntity<Image> createImagen(@RequestBody Image image) {
        return ResponseEntity.ok(imageServiceImpl.saveImage(image));
    }

    @GetMapping
    public ResponseEntity<List<Image>> listAllTheImages(){
        return ResponseEntity.ok(imageServiceImpl.listAllTheImages());
    }

}
