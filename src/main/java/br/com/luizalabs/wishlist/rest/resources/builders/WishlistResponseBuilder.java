package br.com.luizalabs.wishlist.rest.resources.builders;

import br.com.luizalabs.wishlist.domain.model.Wishlist;
import br.com.luizalabs.wishlist.rest.endpoints.CustomerEndpoint;
import br.com.luizalabs.wishlist.rest.endpoints.ProductEndpoint;
import br.com.luizalabs.wishlist.rest.endpoints.WishlistEndpoint;
import br.com.luizalabs.wishlist.rest.resources.ProductsOfWishlistResponse;
import br.com.luizalabs.wishlist.rest.resources.WishlistResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.LinkRelation;
import org.springframework.stereotype.Component;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WishlistResponseBuilder {

    public WishlistResponse toWishlistResponse(final Wishlist wishlist ){
        WishlistResponse response = new WishlistResponse( wishlist );
        response.getProducts().forEach(
                product -> product.add(
                        linkTo(methodOn(ProductEndpoint.class)
                                .findById(product.getProductId())).withSelfRel()
                )
        );
        response.getCustomer().add(
                linkTo(methodOn(CustomerEndpoint.class)
                        .findById(response.getCustomer().getCustomerId())).withSelfRel()
        );

        return response;
    }

    public EntityModel<WishlistResponse> toEntityModel( Wishlist wishlist ){
        return EntityModel
                .of(
                        toWishlistResponse( wishlist ),
                        linkTo(methodOn(WishlistEndpoint.class).findById( wishlist.getWishlistId() )).withSelfRel(),
                        linkTo(methodOn(ProductEndpoint.class).findAll()).withRel(LinkRelation.of("products")),
                        linkTo(methodOn(CustomerEndpoint.class).findAll()).withRel(LinkRelation.of("customers"))
                );
    }

    public ProductsOfWishlistResponse toProductsWishlistResponse(final Wishlist wishlist ){
        ProductsOfWishlistResponse response = new ProductsOfWishlistResponse( wishlist );
        response.getProducts().forEach(
                product -> product.add(
                        linkTo(methodOn(ProductEndpoint.class)
                                .findById(product.getProductId())).withSelfRel()
                )
        );

        return response;
    }

    public EntityModel<ProductsOfWishlistResponse> toEntityModelWishlistProducts(Wishlist wishlist ){
        return EntityModel
                .of(
                        toProductsWishlistResponse( wishlist ),
                        linkTo(methodOn(WishlistEndpoint.class).findById( wishlist.getWishlistId() )).withSelfRel(),
                        linkTo(methodOn(ProductEndpoint.class).findAll()).withRel(LinkRelation.of("products"))
                );
    }

    public URI createLinkById( Wishlist wishlist ) {
        return linkTo(
                methodOn(ProductEndpoint.class).findById(wishlist.getWishlistId())
        ).toUri();
    }
}
