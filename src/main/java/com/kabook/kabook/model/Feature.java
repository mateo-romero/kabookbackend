package com.kabook.kabook.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table (name = "feature")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Feature {

    @Id
    @SequenceGenerator(name = "feature_sequence" , sequenceName = "feature_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String icon;
    @ManyToMany(mappedBy = "features")
    @JsonIgnore
    private List<Product> products;

    public Feature() {
    }

    public Feature(Long id, String name, String icon, Product product) {
        this.id = id;
        this.name= name;
        this.icon = icon;
    }

    public Feature(String name, String icon, Product product) {
        this.name = name;
        this.icon = icon;

    }
}
