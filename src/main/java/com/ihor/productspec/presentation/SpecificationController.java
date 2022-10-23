package com.ihor.productspec.presentation;

import com.ihor.productspec.model.Specification;
import com.ihor.productspec.repository.SpecificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/specifications")
public class SpecificationController {

    @Autowired
    private SpecificationRepository repository;

    @GetMapping
    public ResponseEntity<List<Specification>> getAll() {
        var result = repository.getAll();
        return ResponseEntity.ok(result);
    }
}
