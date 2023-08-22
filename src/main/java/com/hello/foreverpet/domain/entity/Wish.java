package com.hello.foreverpet.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "wish",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public void addProductInWish(Product product) {
        this.products.add(product);
        product.setWish(this);
    }

    @Transactional
    public void deleteProductInWish(Product product) {
        this.products.remove(product);
        product.setWish(null);
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
