package br.com.luizalabs.wishlist.rest.resources.builders;

import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.rest.endpoints.ProductEndpoint;
import br.com.luizalabs.wishlist.rest.resources.ProductRequest;
import br.com.luizalabs.wishlist.rest.resources.ProductResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductResponseBuilder {

    public Product toEntity( ProductRequest resource ){
        return new Product(resource);
    }

    public ProductResponse toResponse(final Product product ){
        return new ProductResponse( product );
    }

    public CollectionModel<EntityModel<ProductResponse>> toCollectModelEntityModel(List<Product> list ) {
        List<EntityModel<ProductResponse>> listReturn = null;
        if (list != null) {
            listReturn = list.stream()
                    .map(customer -> toEntityModel(customer))
                    .collect(Collectors.toList());
        }
        return CollectionModel
                .of(
                        listReturn,
                        linkTo(methodOn(ProductEndpoint.class).findAll()).withSelfRel()
                );
    }

    public EntityModel<ProductResponse> toEntityModel( Product product ){
        return EntityModel
                .of(
                        toResponse(product),
                        linkTo(
                                methodOn(ProductEndpoint.class).findById(product.getProductId())
                        ).withSelfRel()
                );
    }

    public URI createLinkById( Product product ) {
        return linkTo(
                methodOn(ProductEndpoint.class).findById(product.getProductId())
        ).toUri();
    }

}
