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
@Table (name = "category")
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Category {
    @Id
    @SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column
    private String title;
    @NotNull
    @Column
    private String description;
    @NotNull
    @Column
    private String urlImage;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();


    public Category() {
    }

    public Category(String title, String description, String urlImage) {
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
    }

    public Category(Long id, String title, String description, String urlImage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
    }
}
