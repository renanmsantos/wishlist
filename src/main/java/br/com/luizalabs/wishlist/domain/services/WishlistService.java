package br.com.luizalabs.wishlist.domain.services;

import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.model.Wishlist;
import br.com.luizalabs.wishlist.domain.repositories.CustomerRepository;
import br.com.luizalabs.wishlist.domain.repositories.ProductRepository;
import br.com.luizalabs.wishlist.domain.repositories.WishlistRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    private final static Integer MAX_LIMIT_WISHLIST = 20;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public Wishlist addProductToCustomerWishlist( String productId, String customerId){
        Wishlist wishlist = wishlistRepository.findByCustomerId( new ObjectId(customerId) );
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Product> product = productRepository.findById(productId);

        if ( isNecessaryNewWishlistForACustomer( customerId ) ){
            wishlist = new Wishlist(customer.get(), product.get());
        } else{
            if( isNotReachedMaximumLimitWishlist(wishlist) && isNotProductOnCustomerWishlist( productId, customerId) ){
                wishlist.getProducts().add(product.get());
            }
        }
        return wishlistRepository.save( wishlist );
    }

    public Wishlist removeProductFromCustomerWishlist(String productId, String customerId) {
        Optional<Wishlist> wishlist = wishlistRepository.findByCustomerIdAndProductId( new ObjectId(customerId), new ObjectId(productId) );
        if(!wishlist.isEmpty()){
            Wishlist removedWishlist = (Wishlist) wishlist.get()
                    .getProducts().stream()
                    .filter( product -> !product.getProductId().equals(productId) );
            wishlist = Optional.of(removedWishlist);
        }
        return wishlistRepository.save( wishlist.get() );
    }

    public Wishlist findWishlistByCustomerId( String customerId ){
        return wishlistRepository.findByCustomerId( new ObjectId(customerId) );
    }

    public Boolean existsProductOnCustomerWishlist(String productId, String customerId) {
        return !isNotProductOnCustomerWishlist( productId, customerId );
    }

    private boolean isNecessaryNewWishlistForACustomer( String customerId ){
        return wishlistRepository.findByCustomerId( new ObjectId(customerId) ) == null;
    }

    private boolean isNotProductOnCustomerWishlist( String productId, String customerId ){
        return wishlistRepository.findByCustomerIdAndProductId( new ObjectId(customerId), new ObjectId(productId) ).isEmpty();
    }

    private boolean isNotReachedMaximumLimitWishlist( Wishlist wishlist ){
        return wishlist.getProducts().size() < MAX_LIMIT_WISHLIST;
    }

}

