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

@Entity
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;

//    @OneToMany(mappedBy = "cart",fetch = FetchType.LAZY)
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<>();

//    public void addProductInCart(Product product) {
//        this.products.add(product);
//        product.setCart(this);
//    }

    public void addProductInCart(CartProduct cartProduct) {
        cartProduct.setCart(this);
        this.cartProducts.add(cartProduct);
    }


    public void addCartProduct(Product product) {
        CartProduct cartProduct = new CartProduct(product);
        this.cartProducts.add(cartProduct);
        cartProduct.setCart(this);

    }

//    public void deleteProductInCart(Product product) {
//        this.products.remove(product);
//        product.setCart(null);
//    }

    public void deleteProductInCart(CartProduct cartProduct) {
        this.cartProducts.remove(cartProduct);
        cartProduct.setCart(null);
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
