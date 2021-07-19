package br.com.luizalabs.wishlist.rest.endpoints;

import br.com.luizalabs.wishlist.domain.exceptions.ConflictException;
import br.com.luizalabs.wishlist.domain.exceptions.NotFoundException;
import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.domain.services.CustomerService;
import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import br.com.luizalabs.wishlist.rest.resources.CustomerRequest;
import br.com.luizalabs.wishlist.rest.resources.CustomerResponse;
import br.com.luizalabs.wishlist.rest.resources.builders.CustomerResponseBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( produces = MediaTypes.HAL_JSON_VALUE)
public class CustomerEndpoint {

    public static final String CUSTOMER_ENDPOINT = "/rs/customers";
    public static final String CUSTOMER_SELF_ENDPOINT = "/rs/customers/{customerId}";

    final private CustomerService customerService;
    final private CustomerResponseBuilder customerResponseBuilder;

    CustomerEndpoint( CustomerService customerService, CustomerResponseBuilder customerResponseBuilder ){
        this.customerService = customerService;
        this.customerResponseBuilder = customerResponseBuilder;
    }

    @PostMapping( value = CUSTOMER_ENDPOINT)
    public ResponseEntity<EntityModel<CustomerResponse>> saveCustomer( @RequestBody final CustomerRequest requestBody ){

        Customer customer =  customerResponseBuilder.toEntity(requestBody);

        if( customerService.customerAlreadyExists( customer )){
            throw new ConflictException(ErrorEnum.ALREADY_EXISTS, customerResponseBuilder.createLinkById( customer ) );
        }

        customerService.save(customer);

        return ResponseEntity
                .created( customerResponseBuilder.createLinkById(customer) )
                .body( customerResponseBuilder.toEntityModel(customer) );
    }

    @PutMapping( value = CUSTOMER_SELF_ENDPOINT)
    public ResponseEntity<EntityModel<CustomerResponse>> updateCustomer(
            @PathVariable("customerId") final String customerId,
            @RequestBody final CustomerRequest requestBody ){

        Customer customer = customerService.update( customerId, customerResponseBuilder.toEntity(requestBody));

        return ResponseEntity
                .created( customerResponseBuilder.createLinkById(customer)  )
                .body( customerResponseBuilder.toEntityModel(customer) );
    }

    @GetMapping( value = CUSTOMER_ENDPOINT)
    public CollectionModel<EntityModel<CustomerResponse>> findAll(){

        final List<Customer> list = customerService.findAll();
        if( list.isEmpty() ){
            throw new NotFoundException(ErrorEnum.NOT_FOUND);
        }

        return customerResponseBuilder.toCollectModelEntityModel(list);
    }

    @GetMapping( value = CUSTOMER_SELF_ENDPOINT)
    public EntityModel<CustomerResponse> findById(
            @PathVariable("customerId") final String customerId ){

        final Customer customer = customerService.findById( customerId )
                .orElseThrow( () -> new NotFoundException(ErrorEnum.NOT_FOUND) );

        return customerResponseBuilder.toEntityModel(customer);
    }

}
