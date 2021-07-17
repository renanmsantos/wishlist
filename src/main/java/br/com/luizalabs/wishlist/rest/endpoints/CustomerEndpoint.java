package br.com.luizalabs.wishlist.rest.endpoints;

import br.com.luizalabs.wishlist.domain.services.CustomerService;
import br.com.luizalabs.wishlist.rest.resources.*;
import br.com.luizalabs.wishlist.rest.resources.builders.CustomerResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerEndpoint {

    public static final String CUSTOMER_ENDPOINT = "/rs/customers";
    public static final String CUSTOMER_SELF_ENDPOINT = "/rs/customers/{customerId}";

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerResponseBuilder customerResponseBuilder;

    @PostMapping( value = CUSTOMER_ENDPOINT)
    public CustomerResponse saveCustomer(@RequestBody final CustomerRequest requestBody ){
        return customerResponseBuilder.toResource(
                customerService.save(customerResponseBuilder.toEntity(requestBody))
        );
    }

    @PutMapping( value = CUSTOMER_SELF_ENDPOINT)
    public CustomerResponse updateCustomer( @PathVariable("customerId") final String customerId,
                                            @RequestBody final CustomerRequest requestBody ){
        return customerResponseBuilder.toResource(
                customerService.save(customerResponseBuilder.toEntity(requestBody))
        );
    }

    @GetMapping( value = CUSTOMER_ENDPOINT)
    public List<CustomerResponse> findAll(){
        return customerResponseBuilder.toResources(customerService.findAll());
    }

    @GetMapping( value = CUSTOMER_SELF_ENDPOINT)
    public CustomerResponse findById( @PathVariable("customerId") final String customerId ){
        return customerResponseBuilder
                .toResource(customerService.findById( customerId ));
    }

}
