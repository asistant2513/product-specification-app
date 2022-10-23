package com.ihor.productspec.presentation;

import com.ihor.productspec.model.Product;
import com.ihor.productspec.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        var result = repository.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneById(@PathVariable("id") String id) {
        var result = repository.getOneByID(id);
        return ResponseEntity.ok(result);
    }
}
