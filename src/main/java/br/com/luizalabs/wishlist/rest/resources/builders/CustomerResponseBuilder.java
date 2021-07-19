package br.com.luizalabs.wishlist.rest.resources.builders;

import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.rest.endpoints.CustomerEndpoint;
import br.com.luizalabs.wishlist.rest.resources.CustomerRequest;
import br.com.luizalabs.wishlist.rest.resources.CustomerResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerResponseBuilder {

    public Customer toEntity( CustomerRequest request ){
        return new Customer( request );
    }

    public CustomerResponse toResponse(final Customer customer ){
        return new CustomerResponse( customer );
    }

    public CollectionModel<EntityModel<CustomerResponse>> toCollectModelEntityModel(List<Customer> list ) {
        List<EntityModel<CustomerResponse>> listReturn = null;
        if (list != null) {
            listReturn = list.stream()
                    .map(customer -> toEntityModel(customer))
                    .collect(Collectors.toList());
        }
        return CollectionModel
                .of(
                    listReturn,
                    linkTo(methodOn(CustomerEndpoint.class).findAll()).withSelfRel()
                );
    }

    public EntityModel<CustomerResponse> toEntityModel( Customer customer ){
        return EntityModel
                .of(
                        toResponse(customer),
                        linkTo(
                                methodOn(CustomerEndpoint.class).findById(customer.getCustomerId())
                        ).withSelfRel()
                );
    }

    public URI createLinkById(Customer customer) {
        return linkTo(
                methodOn(CustomerEndpoint.class).findById(customer.getCustomerId())
        ).toUri();
    }
}
