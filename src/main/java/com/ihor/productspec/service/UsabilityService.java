package com.ihor.productspec.service;

import com.ihor.productspec.model.DisentanglementModel;
import com.ihor.productspec.model.Product;
import com.ihor.productspec.model.Specification;
import com.ihor.productspec.model.UsabilityModel;
import com.ihor.productspec.repository.ProductRepository;
import com.ihor.productspec.repository.SpecificationRepository;
import com.ihor.productspec.repository.UsabilityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsabilityService {

    private final SpecificationService specificationService;
    private final ProductService productService;

    public UsabilityService(final SpecificationService specificationService,
                            final ProductService productService) {
        this.specificationService = specificationService;
        this.productService = productService;
    }

    public List<UsabilityModel> performStructuralUsability() {
        var result = initializeDisentanglementList();

        var products = productService.getAll();
        var specs = specificationService.getAll();

        for (int i = 0; i < result.size(); i++) {
            var currentComponent = result.get(i);
            var nextInTree = getProductByAssemblyId(products, currentComponent.getAssemblyId());
            if(nextInTree.getProductType().getTypeCode() == 1){
                continue;
            }
            var currentSpecs = getSpecsByTargetId(specs, nextInTree.getProductCode());
            int shift = 1;
            for (var spec : currentSpecs) {
                result.add(i + shift, UsabilityModel.builder()
                        .currentComponent(currentComponent.getComponentId())
                        .componentId(spec.getTargetProduct().getProductCode())
                        .assemblyId(spec.getSourceProduct().getProductCode())
                        .quantity(spec.getQuantity())
                        .nodeLevel(-1)
                        .treeNodeLevel("...")
                        .build());
                if (spec.getSourceProduct().getProductType().getTypeCode() == 1)
                    break;
                shift++;
            }
        }
        return result;
    }

    private List<UsabilityModel> initializeDisentanglementList() {
        List<UsabilityModel> list = new ArrayList<>();
        var componentList = productService.getProductsByTypeId(3);
        componentList.addAll(productService.getProductsByTypeId(4));
        for (var component : componentList) {
            var specs = specificationService.getAllByTargetProductId(component.getProductCode());
            for (var spec : specs) {
                list.add(UsabilityModel.builder()
                                .currentComponent(component.getProductCode())
                                .componentId(component.getProductCode())
                                .assemblyId(spec.getSourceProduct().getProductCode())
                                .quantity(spec.getQuantity())
                                .nodeLevel(-1)
                                .treeNodeLevel("...")
                        .build());
            }
        }
        return list;
    }

    private Product getProductByAssemblyId(final List<Product> products,final String assemblyId) {
        return products.stream().filter(el -> el.getProductCode().equals(assemblyId))
                .collect(Collectors.toList()).get(0);
    }

    private List<Specification> getSpecsByTargetId(List<Specification> specs, String productCode) {
        return specs.stream().filter(el -> el.getTargetProduct().getProductCode().equals(productCode))
                .collect(Collectors.toList());
    }
}
