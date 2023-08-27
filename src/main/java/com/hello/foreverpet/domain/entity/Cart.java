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
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public Cart(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void addProductToCart(Product product) {
        CartProduct cartProduct = new CartProduct(product);
        cartProducts.add(cartProduct);
        cartProduct.setCart(this);
    }

    public void deleteProductInCart(CartProduct cartProduct) {
        this.cartProducts.remove(cartProduct);
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
