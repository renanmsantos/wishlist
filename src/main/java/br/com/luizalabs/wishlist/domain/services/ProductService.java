package br.com.luizalabs.wishlist.domain.services;

import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.save( product );
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(String productId) {
        return productRepository.findById( productId ).get();
    }

}

