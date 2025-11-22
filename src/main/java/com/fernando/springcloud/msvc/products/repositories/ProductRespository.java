package com.fernando.springcloud.msvc.products.repositories;

import com.fernando.springcloud.msvc.products.entities.Product;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;


public interface ProductRespository extends CrudRepository<Product, Long> {

}
