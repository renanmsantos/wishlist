package br.com.luizalabs.wishlist.domain.repositories;

import br.com.luizalabs.wishlist.domain.model.Wishlist;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    @Query("{ 'customer.customerId' : ?0 }")
    Wishlist findByCustomerId( ObjectId customerId );

    @Query("{ 'customer.customerId' : ?0, 'products.productId' : ?1  }")
    Optional<Wishlist> findByCustomerIdAndProductId(ObjectId customerId, ObjectId productId );
}
