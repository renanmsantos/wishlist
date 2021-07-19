package br.com.luizalabs.wishlist.rest.endpoints;

import br.com.luizalabs.wishlist.domain.exceptions.NotFoundException;
import br.com.luizalabs.wishlist.domain.model.Wishlist;
import br.com.luizalabs.wishlist.domain.services.WishlistService;
import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import br.com.luizalabs.wishlist.rest.resources.ProductsOfWishlistResponse;
import br.com.luizalabs.wishlist.rest.resources.WishlistResponse;
import br.com.luizalabs.wishlist.rest.resources.builders.WishlistResponseBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( produces = MediaTypes.HAL_JSON_VALUE)
public class WishlistEndpoint {

    public static final String WISHLIST_ENDPOINT = "/rs/wishlist";
    public static final String WISHLIST_SELF_ENDPOINT = WISHLIST_ENDPOINT + "/{wishlistId}";
    public static final String WISHLIST_BY_CUSTOMER_ENDPOINT = WISHLIST_ENDPOINT + "/customers/{customerId}";
    public static final String WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT = WISHLIST_BY_CUSTOMER_ENDPOINT + "/products/{productId}";
    public static final String PRODUCTS_BY_CUSTOMER_ENDPOINT = WISHLIST_ENDPOINT + "/products/customers/{customerId}";

    final private WishlistService wishlistService;
    final private WishlistResponseBuilder wishlistResponseBuilder;

    WishlistEndpoint( WishlistService wishlistService, WishlistResponseBuilder wishlistResponseBuilder ){
        this.wishlistService = wishlistService;
        this.wishlistResponseBuilder = wishlistResponseBuilder;
    }

   @GetMapping( value = PRODUCTS_BY_CUSTOMER_ENDPOINT)
    public EntityModel<ProductsOfWishlistResponse> findProductsOfWishlistByCustomerId(
            @PathVariable("customerId") String customerId ){

       final Wishlist wishlist = wishlistService.findWishlistByCustomerId( customerId )
               .orElseThrow( () -> new NotFoundException(ErrorEnum.NOT_FOUND) );

       return wishlistResponseBuilder.toEntityModelWishlistProducts( wishlist );
    }

    @GetMapping( value = WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT)
    public Boolean existsProductOnCustomerWishlist( @PathVariable("customerId") String customerId,
                                                    @PathVariable("productId") String productId){
        return wishlistService.existsProductOnCustomerWishlist( productId, customerId );
    }

    @GetMapping( value = WISHLIST_BY_CUSTOMER_ENDPOINT)
    public EntityModel<WishlistResponse> findWishlistByCustomerId( @PathVariable("customerId") String customerId ){

        final Wishlist wishlist = wishlistService.findWishlistByCustomerId( customerId )
                .orElseThrow( () -> new NotFoundException(ErrorEnum.NOT_FOUND) );

        return wishlistResponseBuilder.toEntityModel( wishlist );
    }

    @PostMapping( value = WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT)
    public ResponseEntity<EntityModel<WishlistResponse>> addProductToCustomerWishlist(@PathVariable("customerId") String customerId,
                                                                                     @PathVariable("productId") String productId ){

        Wishlist wishlist = wishlistService.addProductToCustomerWishlist( productId, customerId );

        return ResponseEntity
                .created( wishlistResponseBuilder.createLinkById( wishlist ) )
                .body( wishlistResponseBuilder.toEntityModel( wishlist ) );
    }

    @DeleteMapping( value = WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT)
    public EntityModel<WishlistResponse> removeProductFromCustomerWishlist( @PathVariable("customerId") String customerId,
                                                               @PathVariable("productId") String productId ){
        Wishlist wishlist = wishlistService.removeProductFromCustomerWishlist( productId, customerId );

        return wishlistResponseBuilder.toEntityModel( wishlist );
    }

    @GetMapping( value = WISHLIST_SELF_ENDPOINT)
    public EntityModel<WishlistResponse> findById( @PathVariable("wishlistId") final String wishlistId ){

        final Wishlist wishlist = wishlistService.findById( wishlistId )
                .orElseThrow( () -> new NotFoundException(ErrorEnum.NOT_FOUND) );

        return wishlistResponseBuilder.toEntityModel( wishlist );
    }

}
