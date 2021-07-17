package br.com.luizalabs.wishlist.domain.model;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document( collection = "wishlist")
public class Wishlist implements Serializable {

    @Id
    private String wishlistId;

    @NonNull
    @Field("customer")
    private Customer customer;

    @Field("products")
    private List<Product> products;

    public Wishlist() {}

    public Wishlist(Customer customer, Product product) {
        this.customer = customer;
        this.products = Arrays.asList(product);
    }

    public String getWishlistId() {
        return wishlistId;
    }

    @NonNull
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(@NonNull Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

}
