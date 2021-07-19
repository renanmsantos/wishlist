package br.com.luizalabs.wishlist.rest.resources;

import br.com.luizalabs.wishlist.domain.model.Product;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation( value = "product", collectionRelation = "products")
public class ProductResponse extends RepresentationModel<ProductResponse> {

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
