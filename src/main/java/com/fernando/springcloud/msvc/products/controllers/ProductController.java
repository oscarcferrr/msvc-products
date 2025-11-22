package com.fernando.springcloud.msvc.products.controllers;

import com.fernando.springcloud.msvc.products.entities.Product;
import com.fernando.springcloud.msvc.products.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll(){
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> details(@PathVariable Long id) throws InterruptedException{

        if(id.equals(10L)){
            //return ResponseEntity.notFound().build();
            throw new IllegalStateException("Producto no encontrado");
        }

        if(id.equals(7L)){
            //return ResponseEntity.notFound().build();
            //throw new IllegalStateException("Producto no encontrado");
            TimeUnit.SECONDS.sleep(3L);
        }

        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
         return ResponseEntity.notFound().build();
    }
}
