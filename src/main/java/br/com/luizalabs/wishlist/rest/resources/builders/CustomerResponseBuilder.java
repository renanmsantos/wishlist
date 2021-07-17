package br.com.luizalabs.wishlist.rest.resources.builders;

import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.rest.resources.CustomerRequest;
import br.com.luizalabs.wishlist.rest.resources.CustomerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerResponseBuilder {

    public Customer toEntity(CustomerRequest request ){
        return new Customer( request );
    }

    public CustomerResponse toResource(final Customer customer ){
        return new CustomerResponse( customer );
    }

    public List<CustomerResponse> toResources(Iterable<? extends Customer> list ){
        List<CustomerResponse> resources = new ArrayList<>();
        if ( list != null ){
            list.forEach(
                    customer -> resources.add(new CustomerResponse( customer ))
            );
        }
        return resources;
    }

}
