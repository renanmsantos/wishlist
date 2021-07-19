package br.com.luizalabs.wishlist.domain.services;

import br.com.luizalabs.wishlist.domain.exceptions.NotFoundException;
import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.domain.repositories.CustomerRepository;
import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    final private CustomerRepository customerRepository;

    CustomerService( CustomerRepository customerRepository ){
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(String customerId) {
        return customerRepository.findById( customerId );
    }

    public Boolean customerAlreadyExists(Customer customer) {
        return !customerRepository.findByCpf( customer.getCpf() ).isEmpty();
    }

    public Customer update( String customerId, Customer customer ) {

        Customer costumerDb = findById( customerId ).orElseThrow(
                () -> new NotFoundException( ErrorEnum.NOT_FOUND )
        );

        if( customer.getCpf() != null && !customer.getCpf().isEmpty() ){
            costumerDb.setCpf( customer.getCpf() );
        }
        if( customer.getFirstName() != null && !customer.getFirstName().isEmpty() ){
            costumerDb.setFirstName( customer.getFirstName() );
        }
        if( customer.getLastName() != null && !customer.getLastName().isEmpty() ){
            costumerDb.setLastName( customer.getLastName() );
        }

        return customerRepository.save( costumerDb );

    }

}
