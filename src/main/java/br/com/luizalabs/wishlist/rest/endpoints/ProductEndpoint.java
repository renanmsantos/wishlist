package br.com.luizalabs.wishlist.rest.endpoints;

import br.com.luizalabs.wishlist.domain.exceptions.NotFoundException;
import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.services.ProductService;
import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import br.com.luizalabs.wishlist.rest.resources.ProductRequest;
import br.com.luizalabs.wishlist.rest.resources.ProductResponse;
import br.com.luizalabs.wishlist.rest.resources.builders.ProductResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductEndpoint {

    public static final String PRODUCT_ENDPOINT = "/rs/products";
    public static final String PRODUCT_SELF_ENDPOINT = "/rs/products/{productId}";

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductResponseBuilder productResponseBuilder;

    @PostMapping( value = PRODUCT_ENDPOINT)
    public ProductResponse saveProduct( @RequestBody final ProductRequest requestBody ){
        return productResponseBuilder.toResource(
                productService.save(productResponseBuilder.toEntity( requestBody ))
        );
    }

    @PutMapping( value = PRODUCT_SELF_ENDPOINT)
    public ProductResponse updateProduct( @PathVariable("productId") final String productId, @RequestBody final ProductRequest requestBody ){
        return productResponseBuilder.toResource(
                productService.save(productResponseBuilder.toEntity( requestBody ))
        );
    }

    @GetMapping( value = PRODUCT_ENDPOINT)
    public List<ProductResponse> findAll(){
        return productResponseBuilder.toResources(productService.findAll());
    }

    @GetMapping( value = PRODUCT_SELF_ENDPOINT)
    public ResponseEntity<ProductResponse> findById(@PathVariable("productId") final String productId ){
        final Product product =
                productService.findById( productId )
                        .orElseThrow(() -> new NotFoundException(ErrorEnum.NOT_FOUND) );

        return ResponseEntity.ok(
                productResponseBuilder.toResource(product)
        );
    }

}
