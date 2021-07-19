package br.com.luizalabs.wishlist.rest.resources;

import br.com.luizalabs.wishlist.domain.model.Wishlist;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.stream.Collectors;

public class WishlistResponse extends RepresentationModel<WishlistResponse> {

    private String wishlistId;
    private CustomerResponse customer;
    private List<ProductResponse> products;

    public WishlistResponse() {}

    public WishlistResponse( Wishlist wishlist ){
        if(wishlist != null){
            this.wishlistId = wishlist.getWishlistId();
            this.customer = new CustomerResponse(wishlist.getCustomer());
            this.products = wishlist.getProducts().stream().map( product -> new ProductResponse(product)).collect(Collectors.toList());
        }
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
