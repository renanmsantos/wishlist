package br.com.luizalabs.wishlist.rest.endpoints;

import br.com.luizalabs.wishlist.domain.services.WishlistService;
import br.com.luizalabs.wishlist.rest.resources.*;
import br.com.luizalabs.wishlist.rest.resources.builders.WishlistResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistEndpoint {

    public static final String WISHLIST_ENDPOINT = "/rs/wishlist";
    public static final String WISHLIST_BY_CUSTOMER_ENDPOINT = WISHLIST_ENDPOINT + "/customers/{customerId}";
    public static final String WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT = WISHLIST_BY_CUSTOMER_ENDPOINT + "/products/{productId}";
    public static final String PRODUCTS_BY_CUSTOMER_ENDPOINT = WISHLIST_ENDPOINT + "/products/customers/{customerId}";

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private WishlistResponseBuilder wishlistResponseBuilder;

    @GetMapping( value = PRODUCTS_BY_CUSTOMER_ENDPOINT)
    public ProductsOfWishlistResponse findProductsOfWishlistByCustomerId( @PathVariable("customerId") String customerId ){
        return wishlistResponseBuilder.toProductsResource(
                wishlistService.findWishlistByCustomerId( customerId )
        );
    }

    @GetMapping( value = WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT)
    public Boolean existsProductOnCustomerWishlist( @PathVariable("customerId") String customerId,
                                                    @PathVariable("productId") String productId){
        return wishlistService.existsProductOnCustomerWishlist( productId, customerId );
    }

    @GetMapping( value = WISHLIST_BY_CUSTOMER_ENDPOINT)
    public WishlistResponse findWishlistByCustomerId( @PathVariable("customerId") String customerId ){
        return wishlistResponseBuilder.toResource(
                wishlistService.findWishlistByCustomerId( customerId )
        );
    }

    @PostMapping( value = WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT)
    public WishlistResponse addProductToCustomerWishlist( @PathVariable("customerId") String customerId,
                                                          @PathVariable("productId") String productId ){
        return wishlistResponseBuilder.toResource(
                wishlistService.addProductToCustomerWishlist( productId, customerId )
        );
    }

    @DeleteMapping( value = WISHLIST_BY_CUSTOMER_AND_PRODUCT_ENDPOINT)
    public WishlistResponse removeProductFromCustomerWishlist( @PathVariable("customerId") String customerId,
                                                               @PathVariable("productId") String productId ){
        return wishlistResponseBuilder.toResource(
                wishlistService.removeProductFromCustomerWishlist( productId, customerId )
        );
    }

}
