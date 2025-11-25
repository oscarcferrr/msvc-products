package com.fernando.springcloud.msvc.products.controllers;

import com.fernando.libs.msvc.commons.entities.Product;
import com.fernando.springcloud.msvc.products.services.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



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
            //throw new IllegalStateException("Producto no encontrado");
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

    @PostMapping
    public ResponseEntity<Product>  createProduct(@RequestBody Product product) {       
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        
        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
         return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product){

        Optional<Product> productOptional = productService.findById(id);
        if(productOptional.isPresent()){
            Product productDb = productOptional.orElseThrow();
            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setCreateAt(product.getCreateAt ());
             return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDb));
        }

        return ResponseEntity.notFound().build();
    }

    
}
