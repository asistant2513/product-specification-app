package com.ihor.productspec.service;

import com.ihor.productspec.model.Product;
import com.ihor.productspec.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(final ProductRepository repository) {
        this.repository = repository;
    }

    public Product getById(final String id){
        return repository.getOneByID(id);
    }

    public List<Product> getProductsByTypeId(long typeId) {
        return repository.getAll().stream()
                .filter(product -> product.getProductType().getTypeCode() == typeId)
                .collect(Collectors.toList());
    }
}
