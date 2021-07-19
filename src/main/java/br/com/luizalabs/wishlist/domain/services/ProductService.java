package br.com.luizalabs.wishlist.domain.services;

import br.com.luizalabs.wishlist.domain.exceptions.NotFoundException;
import br.com.luizalabs.wishlist.domain.model.Customer;
import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.repositories.ProductRepository;
import br.com.luizalabs.wishlist.infra.error.ErrorEnum;
import br.com.luizalabs.wishlist.rest.resources.ProductRequest;
import br.com.luizalabs.wishlist.rest.resources.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final private ProductRepository productRepository;

    ProductService( ProductRepository productRepository ){
        this.productRepository = productRepository;
    }

    public Product save(Product product){
        return productRepository.save( product );
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(String productId) {
        return productRepository.findById( productId );
    }

    public boolean productAlreadyExists(Product product) {
        return !productRepository.findByName( product.getName() ).isEmpty();
    }

    public Product update(String productId, ProductRequest product ) {

        Product productDb = findById( productId ).orElseThrow(
                () -> new NotFoundException( ErrorEnum.NOT_FOUND )
        );

        if( product.getName() != null && !product.getName().isEmpty() ){
            productDb.setName( product.getName() );
        }

        return productRepository.save( productDb );

    }
}

