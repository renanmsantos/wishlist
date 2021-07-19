package br.com.luizalabs.wishlist.domain.model;

import br.com.luizalabs.wishlist.rest.resources.ProductRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document( collection = "product" )
public class Product {

    @Id
    private String productId;

    private String name;

    public Product(){}

    public Product(ProductRequest request) {
        this.name = request.getName();
    }

    public Product(String productId) {
        this.productId = productId;
    }

    public Product(String productId, String name) {
        this.productId = productId;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
