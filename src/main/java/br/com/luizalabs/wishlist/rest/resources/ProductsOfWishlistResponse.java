package br.com.luizalabs.wishlist.rest.resources;

import br.com.luizalabs.wishlist.domain.model.Wishlist;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsOfWishlistResponse extends RepresentationModel<ProductsOfWishlistResponse> {

    private List<ProductResponse> products;

    public ProductsOfWishlistResponse() {}

    public ProductsOfWishlistResponse(Wishlist wishlist) {
        this.products = wishlist.getProducts().stream().map( product -> new ProductResponse(product)).collect(Collectors.toList());
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
