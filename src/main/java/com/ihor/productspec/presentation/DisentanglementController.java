package com.ihor.productspec.presentation;

import com.ihor.productspec.model.DisentanglementModel;
import com.ihor.productspec.service.StructuralDisentanglementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/disentanglement")
public class DisentanglementController {

    @Autowired
    private StructuralDisentanglementService disentanglementService;

    @GetMapping("/structural")
    public ResponseEntity<List<DisentanglementModel>> getStructuralDisentanglement(){
        return ResponseEntity.ok(disentanglementService.performStructuralDisentanglement());
    }
}
