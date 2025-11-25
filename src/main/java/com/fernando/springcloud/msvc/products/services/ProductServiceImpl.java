package com.fernando.springcloud.msvc.products.services;

import com.fernando.libs.msvc.commons.entities.Product;
import com.fernando.springcloud.msvc.products.repositories.ProductRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.core.env.Environment;

@Service
public class ProductServiceImpl implements ProductService{

    final private ProductRespository repository;
    final private Environment environment;

    public ProductServiceImpl(ProductRespository repository, Environment enviroment){
        this.repository = repository;
        this.environment = enviroment;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return ((List<Product>) repository.findAll()).stream().map(product -> {
            product.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(product -> {
            product.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
            return product;
        });
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return this.repository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    
}
