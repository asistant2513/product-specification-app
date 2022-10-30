package com.ihor.productspec.repository;

import com.ihor.productspec.mapping.ProductMapper;
import com.ihor.productspec.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_PRODUCTS;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ONE_PRODUCT_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.ADD_PRODUCT_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_PRODUCT_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_PRODUCT_RECORD;


@Repository
public class ProductRepository {

    private final JdbcTemplate template;

    private final ProductMapper mapper;

    public ProductRepository(final JdbcTemplate template,
                             final ProductMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public List<Product> getAll() {
        return template.query(SELECT_ALL_PRODUCTS, mapper);
    }

    public Product getOneByID(final String id) {
        return template.query(SELECT_ONE_PRODUCT_BY_ID,
                ps -> ps.setString(1, id),
                mapper).get(0);
    }

    public int addOne(final String id, final String name, long typeId) {
        return template.update(ADD_PRODUCT_RECORD, ps -> {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setLong(3, typeId);
        });
    }

    int update(final String id, final String name, long typeId) {
        return template.update(UPDATE_PRODUCT_RECORD, ps -> {
            ps.setString(1, name);
            ps.setLong(2, typeId);
            ps.setString(3, id);
        });
    }

    int deleteOneByID(final String id) {
        return template.update(DELETE_PRODUCT_RECORD,
                ps -> ps.setString(1, id));
    }
}
