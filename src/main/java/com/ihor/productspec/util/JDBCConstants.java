package com.ihor.productspec.util;

public final class JDBCConstants {

    public static final String SELECT_ALL_PRODUCT_TYPES = "SELECT * FROM product_types;";

    public static final String SELECT_ONE_PRODUCT_TYPE_BY_ID = "SELECT * FROM product_types AS PT WHERE PT.type_id = %s;";

    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM main_products as MP INNER JOIN product_types AS PT ON MP.product_type_id = PT.type_id;";

    public static final String SELECT_ONE_PRODUCT_BY_ID = "SELECT * FROM main_products as MP INNER JOIN product_types AS PT ON MP.product_type_id = PT.type_id WHERE MP.product_id = '%s';";

    public static final String SELECT_ALL_SPECS = "SELECT MP1.product_id AS source_id, MP1.product_name AS source_name, MP1.product_type_id AS source_type_id, PT1.type_name AS source_type_name, " +
            "MP2.product_id AS target_id, MP2.product_name AS target_name, MP2.product_type_id AS target_type_id, PT2.type_name AS target_type_name, quantity " +
            "FROM product_spec AS PS " +
            "INNER JOIN main_products AS MP1 " +
            "ON PS.source_product_id = MP1.product_id " +
            "INNER JOIN main_products AS MP2 " +
            "ON PS.target_product_id = MP2.product_id " +
            "INNER JOIN product_types AS PT1 " +
            "ON MP1.product_type_id = PT1.type_id " +
            "INNER JOIN product_types AS PT2 " +
            "ON MP2.product_type_id = PT2.type_id;";

    public static final String SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID = "SELECT " +
            "PS.target_product_id AS component_id, " +
            "MP.product_name AS component_name, " +
            "quantity " +
            "FROM product_spec AS PS " +
            "INNER JOIN main_products AS MP " +
            "ON PS.target_product_id = MP.product_id " +
            "WHERE PS.source_product_id = '%s';";

    public static final String SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID = "SELECT " +
            "PS.source_product_id AS component_id, " +
            "MP.product_name AS component_name, " +
            "quantity " +
            "FROM product_spec AS PS " +
            "INNER JOIN main_products AS MP " +
            "ON PS.source_product_id = MP.product_id " +
            "WHERE PS.target_product_id = '%s';";
}
