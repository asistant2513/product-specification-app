package com.ihor.productspec.service;

import com.ihor.productspec.model.DisentanglementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StructuralDisentanglementService {

    @Autowired
    private ProductService productService;

    @Autowired
    private SpecificationService specificationService;

    public List<DisentanglementModel> performStructuralDisentanglement() {
        List<DisentanglementModel> result = initializeDisentanglementList();
        int level = 2;
        for (int i = 0; i < result.size(); i++) {
            var currentComponent = productService.getById(result.get(i).getComponentId());
            if (currentComponent.getProductType().getTypeCode() == 1) {
                level = 2;
            }
            if (currentComponent.getProductType().getTypeCode() == 3 ||
                    currentComponent.getProductType().getTypeCode() == 4) {
                level = 2;
                continue;
            }
            var componentSpecList = specificationService.getAllBySourceProductId(currentComponent.getProductCode());
            int shift = 1;
            for (var spec : componentSpecList) {
                result.add(i + shift, DisentanglementModel.builder()
                        .productId(result.get(i).getProductId())
                        .assemblyId(spec.getSourceProduct().getProductCode())
                        .componentId(spec.getTargetProduct().getProductCode())
                        .quantity(spec.getQuantity())
                        .nodeLevel(level)
                        .treeNodeLevel(".".repeat(level) + level)
                        .build());
                shift++;
            }
            level++;
        }
        return result;
    }

    private List<DisentanglementModel> initializeDisentanglementList() {
        List<DisentanglementModel> list = new ArrayList<>();
        var assemblyList = productService.getProductsByTypeId(1);
        for (var assembly : assemblyList) {
            var specs = specificationService.getAllBySourceProductId(assembly.getProductCode());
            for (var spec : specs) {
                list.add(DisentanglementModel.builder()
                        .productId(assembly.getProductCode())
                        .assemblyId(spec.getSourceProduct().getProductCode())
                        .componentId(spec.getTargetProduct().getProductCode())
                        .quantity(spec.getQuantity())
                        .nodeLevel(1)
                        .treeNodeLevel(".1")
                        .build());
            }
        }
        return list;
    }

}
