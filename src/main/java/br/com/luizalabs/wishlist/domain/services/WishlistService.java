package br.com.luizalabs.wishlist.domain.services;

import br.com.luizalabs.wishlist.domain.exceptions.BadRequestException;
import br.com.luizalabs.wishlist.domain.exceptions.ConflictException;
import br.com.luizalabs.wishlist.domain.exceptions.NotFoundException;
import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.model.Wishlist;
import br.com.luizalabs.wishlist.domain.repositories.CustomerRepository;
import br.com.luizalabs.wishlist.domain.repositories.ProductRepository;
import br.com.luizalabs.wishlist.domain.repositories.WishlistRepository;
import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    final private WishlistRepository wishlistRepository;
    final private CustomerRepository customerRepository;
    final private ProductRepository productRepository;

    private final static Integer MAX_LIMIT_WISHLIST = 20;

    WishlistService( WishlistRepository wishlistRepository, CustomerRepository customerRepository, ProductRepository productRepository ){
        this.wishlistRepository = wishlistRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Wishlist addProductToCustomerWishlist( String productId, String customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException(ErrorEnum.NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(ErrorEnum.NOT_FOUND));

        Wishlist wishlist = wishlistRepository.findByCustomerId( new ObjectId(customerId) ).orElse(
                new Wishlist(customer)
        );

        if( isProductOnCustomerWishlist( productId, customerId) ){
            throw new ConflictException(ErrorEnum.ALREADY_EXISTS.getExceptionWordsMessage());
        }
        if( isReachedMaximumLimitWishlist(wishlist)){
            throw new BadRequestException(ErrorEnum.MAX_WISHLIST_REACHED.getExceptionWordsMessage());
        }

        wishlist.getProducts().add(product);

        return wishlistRepository.save( wishlist );
    }

    public Wishlist removeProductFromCustomerWishlist(String productId, String customerId) {
        Wishlist wishlist = wishlistRepository
                .findByCustomerIdAndProductId( new ObjectId(customerId), new ObjectId(productId) )
                .orElseThrow(() -> new NotFoundException(ErrorEnum.NOT_FOUND));
        List<Product> products = wishlist.getProducts().stream()
                .filter(product -> !product.getProductId().equals(productId) ).collect(Collectors.toList());
        wishlist.setProducts(products);
        return wishlistRepository.save( wishlist );
    }

    public Optional<Wishlist> findWishlistByCustomerId( String customerId ){
        return wishlistRepository.findByCustomerId( new ObjectId(customerId) );
    }

    public Boolean existsProductOnCustomerWishlist(String productId, String customerId) {
        return isProductOnCustomerWishlist( productId, customerId );
    }

    private boolean isNecessaryNewWishlistForACustomer( String customerId ){
        return wishlistRepository.findByCustomerId( new ObjectId(customerId) ) == null;
    }

    private boolean isProductOnCustomerWishlist( String productId, String customerId ){
        return !wishlistRepository.findByCustomerIdAndProductId( new ObjectId(customerId), new ObjectId(productId) ).isEmpty();
    }

    private boolean isReachedMaximumLimitWishlist( Wishlist wishlist ){
        return wishlist.getProducts().size() >= MAX_LIMIT_WISHLIST;
    }

    public Optional<Wishlist> findById(String wishlistId) {
        return wishlistRepository.findById( wishlistId );
    }
}

