package com.ihor.productspec.presentation;

import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.service.ProductTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/productTypes")
public class ProductTypeController {

    private final ProductTypeService service;

    public ProductTypeController(final ProductTypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductType>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody final ProductType productType) {
        return ResponseEntity.ok(service.addOne(productType));
    }

    @PatchMapping
    public ResponseEntity<Integer> update(@RequestBody final ProductType productType) {
        var result = service.update(productType);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
