package br.com.luizalabs.wishlist.domain.repositories;

import br.com.luizalabs.wishlist.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository <Customer, String> {
    Optional<Customer> findByCpf( String cpf );
}
