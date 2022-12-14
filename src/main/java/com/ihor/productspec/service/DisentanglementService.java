package com.ihor.productspec.service;

import com.ihor.productspec.model.DisentanglementModel;
import com.ihor.productspec.model.Product;
import com.ihor.productspec.model.Specification;
import com.ihor.productspec.model.TotalDisentanglementModel;
import com.ihor.productspec.repository.DisentanglementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisentanglementService {

    private final ProductService productService;

    private final SpecificationService specificationService;

    private final DisentanglementRepository disentanglementRepository;

    public DisentanglementService(final ProductService productService,
                                  final SpecificationService specificationService,
                                  final DisentanglementRepository disentanglementRepository) {
        this.productService = productService;
        this.specificationService = specificationService;
        this.disentanglementRepository = disentanglementRepository;
    }

    public List<DisentanglementModel> performStructuralDisentanglement(boolean saveToDb) {
        var result = performStructuralDisentanglement();
        if (saveToDb) {
            saveAll(result);
        }
        return result;
    }

    private int[] saveAll(final List<DisentanglementModel> list) {
        return disentanglementRepository.addAll(list);
    }

    private List<DisentanglementModel> performStructuralDisentanglement() {
        var result = initializeDisentanglementList();
        var productList = productService.getAll();
        var specificationsList = specificationService.getAll();

        int level = 2;
        for (int i = 0; i < result.size(); i++) {
            var currentComponent = getCurrentComponent(productList, result.get(i).getComponentId());
            if (currentComponent.getProductType().getTypeCode() == 1) {
                level = 2;
            }
            if (currentComponent.getProductType().getTypeCode() == 3 ||
                    currentComponent.getProductType().getTypeCode() == 4) {
                level = 2;
                continue;
            }
            var componentSpecList = getAllBySourceProductId(specificationsList, currentComponent.getProductCode());
            int shift = 1;
            for (var spec : componentSpecList) {
                result.add(i + shift, DisentanglementModel.builder()
                        .productId(result.get(i).getProductId())
                        .assemblyId(spec.getSourceProduct().getProductCode())
                        .componentId(spec.getTargetProduct().getProductCode())
                        .quantity(result.get(i).getQuantity() * spec.getQuantity())
                        .nodeLevel(level)
                        .treeNodeLevel(".".repeat(level) + level)
                        .build());
                shift++;
            }
            level++;
        }
        return result;
    }

    public List<TotalDisentanglementModel> getTotalDisentanglement() {
        return disentanglementRepository.getTotalDisentanglement();
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

    private Product getCurrentComponent(final List<Product> list, final String id) {
        return list.stream()
                .filter(product -> product.getProductCode().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    private List<Specification> getAllBySourceProductId(final List<Specification> list, final String id) {
        return list.stream()
                .filter(spec -> spec.getSourceProduct().getProductCode().equals(id))
                .collect(Collectors.toList());
    }
}
