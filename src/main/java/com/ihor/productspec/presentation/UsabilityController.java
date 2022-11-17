package com.ihor.productspec.presentation;

import com.ihor.productspec.model.UsabilityModel;
import com.ihor.productspec.service.UsabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usability")
public class UsabilityController {

    private final UsabilityService service;

    public UsabilityController(UsabilityService service) {
        this.service = service;
    }

    @GetMapping("/structural")
    public ResponseEntity<List<UsabilityModel>> getStructuralUsability() {
        return ResponseEntity.ok(service.performStructuralUsability());
    }
}
