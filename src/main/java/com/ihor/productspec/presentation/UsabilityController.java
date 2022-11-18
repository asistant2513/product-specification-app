package com.ihor.productspec.presentation;

import com.ihor.productspec.model.DisentanglementModel;
import com.ihor.productspec.model.TotalDisentanglementModel;
import com.ihor.productspec.service.UsabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usability")
public class UsabilityController {

    private final UsabilityService service;

    public UsabilityController(UsabilityService service) {
        this.service = service;
    }

    @PostMapping("/structural")
    public ResponseEntity<List<DisentanglementModel>> getStructuralUsability(@RequestParam("saveToDB") boolean save) {
        return ResponseEntity.ok(service.performStructuralUsability(save));
    }

    @GetMapping("/total")
    public ResponseEntity<List<TotalDisentanglementModel>> getTotalUsability() {
        return ResponseEntity.ok(service.performTotalUsability());
    }
}
