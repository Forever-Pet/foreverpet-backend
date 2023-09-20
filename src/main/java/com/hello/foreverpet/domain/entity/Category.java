package com.hello.foreverpet.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    private boolean active;

    public Category(String name) {
        this.name = name;
        products = new ArrayList<>();
        this.active = true;
    }

    public void updateCategory(String categoryName) {
        this.name = categoryName;
    }

    public void unActive() {
        this.active = false;
    }

    public void active() {
        this.active = true;
    }
}
