package com.ihor.productspec.presentation;

import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.repository.ProductTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProductType> getById(@PathVariable("id") Long id) {
        var result = repository.getOneByID(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public int add(@RequestBody final ProductType productType) {
        return repository.addOne(productType);
    }

    @PatchMapping
    public ResponseEntity<ProductType> update(@RequestBody final ProductType productType) {
        var result = repository.update(productType);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") final Long id) {
        return repository.deleteOneByID(id);
    }
}
