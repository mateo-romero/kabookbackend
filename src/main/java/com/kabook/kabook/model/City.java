package com.kabook.kabook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})

public class City {
    @Id
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String country;

    //una ciudad tiene muchos prod
    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();



    public City() {
    }

    public City(Long id, String name, String country, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.products = products;
    }

    public City(String name, String country, Set<Product> products) {
        this.name = name;
        this.country = country;
        this.products = products;
    }
}
