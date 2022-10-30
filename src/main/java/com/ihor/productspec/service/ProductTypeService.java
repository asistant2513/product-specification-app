package com.ihor.productspec.service;

import com.ihor.productspec.model.ProductType;
import com.ihor.productspec.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    private final ProductTypeRepository repository;

    public ProductTypeService(ProductTypeRepository repository) {
        this.repository = repository;
    }

    public List<ProductType> getAll() {
        return repository.getAll();
    }

    public ProductType getById(long id) {
        return repository.getOneByID(id);
    }

    public int addOne(final ProductType productType) {
        return repository.addOne(productType.getTypeCode(), productType.getTypeName());
    }

    public int update(final ProductType productType) {
        return repository.update(productType.getTypeCode(), productType.getTypeName());
    }

    public int deleteById(long id) {
        return repository.deleteOneByID(id);
    }
}
