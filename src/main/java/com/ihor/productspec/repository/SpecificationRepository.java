package com.ihor.productspec.repository;

import com.ihor.productspec.model.Specification;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_SPECS;
import static com.ihor.productspec.util.JDBCConstants.ADD_SPEC_RECORD;
import static com.ihor.productspec.util.JDBCConstants.UPDATE_SPEC_RECORD;
import static com.ihor.productspec.util.JDBCConstants.DELETE_SPEC_RECORD;

@Repository
public interface SpecificationRepository {

    @Query(SELECT_ALL_SPECS)
    List<Specification> getAll();

    @Query(ADD_SPEC_RECORD)
    int addOne(@Param("sourceId") String sourceProductId,
               @Param("targetId") String targetProductId,
               @Param("quantity") long quantity);

    @Query(UPDATE_SPEC_RECORD)
    int update(@Param("sourceId") String sourceProductId,
               @Param("targetId") String targetProductId,
               @Param("quantity") long quantity);

    @Query(DELETE_SPEC_RECORD)
    int deleteOne(Specification item);
}
