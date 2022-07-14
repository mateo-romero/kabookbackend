package com.kabook.kabook.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Image {

    @Id
    @SequenceGenerator(name = "image_sequence", sequenceName = "image_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String titleImage;
    @Column
    private String descriptionImage;
    @Column
    private String urlImage;

    //tenemos que hacer el manytoone aca? De muchas imag -> a un producto.
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public Image() {
    }

    public Image(Long id, String titleImage, String descriptionImage, String urlImage) {
        this.id = id;
        this.titleImage = titleImage;
        this.descriptionImage = descriptionImage;
        this.urlImage = urlImage;
    }

    public Image(String titleImage, String descriptionImage, String urlImage) {
        this.titleImage = titleImage;
        this.descriptionImage = descriptionImage;
        this.urlImage = urlImage;
    }
}
