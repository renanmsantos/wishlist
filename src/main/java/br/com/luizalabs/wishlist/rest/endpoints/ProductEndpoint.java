package br.com.luizalabs.wishlist.rest.endpoints;

import br.com.luizalabs.wishlist.domain.exceptions.ConflictException;
import br.com.luizalabs.wishlist.domain.exceptions.NotFoundException;
import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.services.ProductService;
import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import br.com.luizalabs.wishlist.rest.resources.ProductRequest;
import br.com.luizalabs.wishlist.rest.resources.ProductResponse;
import br.com.luizalabs.wishlist.rest.resources.builders.ProductResponseBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( produces = MediaTypes.HAL_JSON_VALUE)
public class ProductEndpoint {

    public static final String PRODUCT_ENDPOINT = "/rs/products";
    public static final String PRODUCT_SELF_ENDPOINT = "/rs/products/{productId}";

    private final ProductService productService;
    private final ProductResponseBuilder productResponseBuilder;

    ProductEndpoint( ProductService productService, ProductResponseBuilder productResponseBuilder ){
        this.productService = productService;
        this.productResponseBuilder = productResponseBuilder;
    }

    @PostMapping( value = PRODUCT_ENDPOINT)
    public ResponseEntity<EntityModel<ProductResponse>> saveProduct( @RequestBody final ProductRequest requestBody ){

        Product product =  productResponseBuilder.toEntity( requestBody );

        if( productService.productAlreadyExists( product )){
            throw new ConflictException(ErrorEnum.ALREADY_EXISTS, productResponseBuilder.createLinkById( product ) );
        }

        productService.save( product );

        return ResponseEntity
                .created( productResponseBuilder.createLinkById(product) )
                .body( productResponseBuilder.toEntityModel(product) );
    }

    @PutMapping( value = PRODUCT_SELF_ENDPOINT)
    public ResponseEntity<EntityModel<ProductResponse>> updateProduct(
            @PathVariable("productId") final String productId,
            @RequestBody final ProductRequest requestBody ){

        Product product = productService.update( productId, requestBody );

        return ResponseEntity
                .created( productResponseBuilder.createLinkById(product) )
                .body( productResponseBuilder.toEntityModel(product) );

    }

    @GetMapping( value = PRODUCT_ENDPOINT)
    public CollectionModel<EntityModel<ProductResponse>> findAll(){

        final List<Product> list = productService.findAll();
        if( list.isEmpty() ){
            throw new NotFoundException(ErrorEnum.NOT_FOUND);
        }

        return productResponseBuilder.toCollectModelEntityModel(list);
    }

    @GetMapping( value = PRODUCT_SELF_ENDPOINT)
    public EntityModel<ProductResponse> findById( @PathVariable("productId") final String productId ){

        final Product product = productService.findById( productId )
                .orElseThrow( () -> new NotFoundException(ErrorEnum.NOT_FOUND) );

        return productResponseBuilder.toEntityModel( product );
    }

}
