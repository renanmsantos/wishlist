package br.com.luizalabs.wishlist.rest.resources;

import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.model.Wishlist;

import java.util.List;

public class WishlistResponse {

    private Customer customer;
    private List<Product> products;

    public WishlistResponse() {}

    public WishlistResponse( Wishlist wishlist ){
        if(wishlist != null){
            this.customer = wishlist.getCustomer();
            this.products = wishlist.getProducts();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
