package br.com.luizalabs.wishlist.rest.resources.builders;

import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.rest.resources.ProductRequest;
import br.com.luizalabs.wishlist.rest.resources.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductResponseBuilder {

    public Product toEntity( ProductRequest resource ){
        return new Product(resource);
    }

    public ProductResponse toResource(final Product product ){
        return new ProductResponse( product );
    }

    public List<ProductResponse> toResources(Iterable<? extends Product> list ){
        List<ProductResponse> resources = new ArrayList<>();
        if ( list != null ){
            list.forEach(
                    product -> resources.add(new ProductResponse(product))
            );
        }
        return resources;
    }

}
