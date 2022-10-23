package com.ihor.productspec.presentation;

import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.repository.ProductTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/productTypes")
public class ProductTypeController {

    @Autowired
    private ProductTypeRepository repository;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAll() {
        var result = repository.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getById(@PathVariable("id") Integer id) {
        var result = repository.getOneByID(id);
        return ResponseEntity.ok(result);
    }
}
