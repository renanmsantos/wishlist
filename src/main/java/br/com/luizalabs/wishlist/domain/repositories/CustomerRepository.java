package br.com.luizalabs.wishlist.domain.repositories;

import br.com.luizalabs.wishlist.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository <Customer, String> { }
