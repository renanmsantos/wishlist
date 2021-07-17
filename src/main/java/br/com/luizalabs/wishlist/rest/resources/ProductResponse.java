package br.com.luizalabs.wishlist.rest.resources;

import br.com.luizalabs.wishlist.domain.model.Product;

public class ProductResponse {
    private String productId;
    private String name;

    public ProductResponse() {}

    public ProductResponse(Product product ){
        this.productId = product.getProductId();
        this.name = product.getName();
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
