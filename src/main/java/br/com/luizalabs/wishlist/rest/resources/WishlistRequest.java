package br.com.luizalabs.wishlist.rest.resources;

import java.util.List;

public class WishlistRequest {

    private List<ProductRequest> products;

    public List<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRequest> products) {
        this.products = products;
    }
}
