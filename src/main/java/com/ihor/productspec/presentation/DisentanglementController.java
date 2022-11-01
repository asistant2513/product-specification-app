package com.ihor.productspec.presentation;

import com.ihor.productspec.model.DisentanglementModel;
import com.ihor.productspec.model.TotalDisentanglementModel;
import com.ihor.productspec.service.DisentanglementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/disentanglement")
public class DisentanglementController {

    @Autowired
    private DisentanglementService disentanglementService;

    @PostMapping("/structural")
    public ResponseEntity<List<DisentanglementModel>> getStructuralDisentanglement(@RequestParam("save") boolean saveToDb) {
        return ResponseEntity.ok(disentanglementService.performStructuralDisentanglement(saveToDb));
    }

    @GetMapping("/total")
    public ResponseEntity<List<TotalDisentanglementModel>> getTotalDisentanglement() {
        return ResponseEntity.ok(disentanglementService.getTotalDisentanglement());
    }
}
