package br.com.luizalabs.wishlist.rest.resources.builders;

import br.com.luizalabs.wishlist.domain.model.Wishlist;
import br.com.luizalabs.wishlist.rest.resources.ProductsOfWishlistResponse;
import br.com.luizalabs.wishlist.rest.resources.WishlistResponse;
import org.springframework.stereotype.Component;

@Component
public class WishlistResponseBuilder {

    public WishlistResponse toResource(final Wishlist wishlist ){
        return new WishlistResponse( wishlist );
    }

    public ProductsOfWishlistResponse toProductsResource(Wishlist wishlist) {
        return new ProductsOfWishlistResponse(wishlist);
    }
}
