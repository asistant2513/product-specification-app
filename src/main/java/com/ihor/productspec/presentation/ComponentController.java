package com.ihor.productspec.presentation;

import com.ihor.productspec.model.Component;
import com.ihor.productspec.repository.ComponentRepository;
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
@RequestMapping("/components")
public class ComponentController {

    @Autowired
    private ComponentRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<List<Component>> getAllFirstLevelNodeComponents(@PathVariable("id") String id) {
        var result = repository.getAllFirstLevelNodesById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/usability/{id}")
    public ResponseEntity<List<Component>> getAllFirstLevelUsabilityComponents(@PathVariable("id") String id) {
        var result = repository.getAllFirstLevelUsabilityById(id);
        return ResponseEntity.ok(result);
    }
}
