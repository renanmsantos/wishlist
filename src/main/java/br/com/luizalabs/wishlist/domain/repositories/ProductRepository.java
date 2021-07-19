package br.com.luizalabs.wishlist.domain.repositories;

import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository <Product, String> {
    Optional<Product> findByName(String name);
}
