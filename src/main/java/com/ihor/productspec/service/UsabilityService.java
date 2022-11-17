package com.ihor.productspec.service;

import com.ihor.productspec.model.UsabilityModel;
import com.ihor.productspec.repository.ProductRepository;
import com.ihor.productspec.repository.SpecificationRepository;
import com.ihor.productspec.repository.UsabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsabilityService {

    private final UsabilityRepository repository;

    private final SpecificationRepository specificationRepository;

    private final ProductRepository productRepository;

    public UsabilityService(final UsabilityRepository repository,
                            final SpecificationRepository specificationRepository,
                            final ProductRepository productRepository) {
        this.repository = repository;
        this.specificationRepository = specificationRepository;
        this.productRepository = productRepository;
    }

    public List<UsabilityModel> performStructuralUsability() {
        var result = repository.getAllLastLevelNodes();

        var specifications = specificationRepository.getAll();
        var products = productRepository.getAll();

        //TODO: create algorithm

        return result;
    }
}
