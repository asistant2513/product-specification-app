package com.ihor.productspec.service;

import com.ihor.productspec.model.Specification;
import com.ihor.productspec.repository.SpecificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecificationService {

    private final SpecificationRepository repository;

    public SpecificationService(final SpecificationRepository repository) {
        this.repository = repository;
    }

    public List<Specification> getAll() {
        return repository.getAll();
    }

    public List<Specification> getAllBySourceProductId(final String id) {
        return repository.getAll().stream()
                .filter(spec -> spec.getSourceProduct().getProductCode().equals(id))
                .collect(Collectors.toList());
    }

    public List<Specification> getAllByTargetProductId(final String id) {
        return repository.getAll().stream()
                .filter(spec -> spec.getTargetProduct().getProductCode().equals(id))
                .collect(Collectors.toList());
    }
}
