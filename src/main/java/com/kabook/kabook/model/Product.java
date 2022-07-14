package com.kabook.kabook.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence" , sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column
    private String name;
    @NotNull
    @Column
    private String description;
    @NotNull
    @Column
    private String location;
    @Column
    private String latitude;
    @Column
    private String longitude;
    @NotNull
    @Column
    private String rating;
    @Column
    private Boolean isActive;
    // Muchos productos en una categoria
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
    // Muchos prod en una ciudad.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    private City city;
    //Un producto muchas imagenes
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Image> images;
    // Muchos a muchos producto-caracteristicas
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="product_feature",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="feature_id"))
    private Set<Feature> features;
    //Un producto muchas reservas
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Booking> bookings;
    // Un producto muchas politicas
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Policy> policies;

    public Product(String name, String description, String location, String latitude, String longitude, String rating, Boolean isActive, Category category, City city, Set<Feature> features) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.isActive = isActive;
        this.category = category;
        this.city = city;
        this.features = features;
    }
}
