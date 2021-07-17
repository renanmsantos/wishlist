package br.com.luizalabs.wishlist.rest.resources;

import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.model.Wishlist;

import java.util.List;

public class ProductsOfWishlistResponse {

    private List<Product> products;

    public ProductsOfWishlistResponse() {}

    public ProductsOfWishlistResponse(Wishlist wishlist) {
        this.products = wishlist.getProducts();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
