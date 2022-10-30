package com.ihor.productspec.repository;

import com.ihor.productspec.mapping.ProductTypeMapper;
import com.ihor.productspec.model.ProductType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_PRODUCT_TYPES;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ONE_PRODUCT_TYPE_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.ADD_PRODUCT_TYPE_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_PRODUCT_TYPE_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_PRODUCT_TYPE_RECORD;

@Repository
public class ProductTypeRepository {

    private final JdbcTemplate template;

    private final ProductTypeMapper mapper;

    public ProductTypeRepository(final JdbcTemplate template,
                                 final ProductTypeMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public List<ProductType> getAll() {
        return template.query(SELECT_ALL_PRODUCT_TYPES, mapper);
    }

    public ProductType getOneByID(long id) {
        return template.query(SELECT_ONE_PRODUCT_TYPE_BY_ID,
                ps -> ps.setLong(1, id),
                mapper).get(0);
    }

    public int addOne(long typeId, final String typeName) {
        return template.update(ADD_PRODUCT_TYPE_RECORD, ps -> {
            ps.setLong(1, typeId);
            ps.setString(2, typeName);
        });
    }

    public int update(long typeId, final String typeName) {
        return template.update(UPDATE_PRODUCT_TYPE_RECORD, ps -> {
            ps.setString(1, typeName);
            ps.setLong(2, typeId);
        });
    }

    public int deleteOneByID(long id) {
        return template.update(DELETE_PRODUCT_TYPE_RECORD, ps -> {
            ps.setLong(1, id);
        });
    }
}
