package com.ihor.productspec.repository;

import com.ihor.productspec.model.Component;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID;
import static com.ihor.productspec.util.JDBCConstants.SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID;

@Repository
public interface ComponentRepository {

    @Query(SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID)
    List<Component> getAllFirstLevelNodesById(@Param("productId") String id);

    @Query(SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID)
    List<Component> getAllFirstLevelUsabilityById(@Param("productId") String id);
}
