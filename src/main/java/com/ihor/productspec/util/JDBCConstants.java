package com.ihor.productspec.util;

public final class JDBCConstants {
    /* -----# Product type queries section #----- */
    public static final String SELECT_ALL_PRODUCT_TYPES = "SELECT * FROM product_types;";

    public static final String SELECT_ONE_PRODUCT_TYPE_BY_ID = "SELECT * FROM product_types AS PT WHERE PT.type_id = ?;";

    public static final String ADD_PRODUCT_TYPE_RECORD = "INSERT INTO product_types (type_id, type_name) VALUES (?, ?);";

    public static final String UPDATE_PRODUCT_TYPE_RECORD = "UPDATE product_types SET type_name = ? WHERE type_id = ?;";

    public static final String DELETE_PRODUCT_TYPE_RECORD = "DELETE FROM product_types WHERE type_id = ?;";

    /* -----# Product queries section #----- */
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM main_products as MP INNER JOIN product_types AS PT ON MP.product_type_id = PT.type_id;";

    public static final String SELECT_ONE_PRODUCT_BY_ID = "SELECT * FROM main_products as MP INNER JOIN product_types AS PT ON MP.product_type_id = PT.type_id WHERE MP.product_id = ?;";

    public static final String ADD_PRODUCT_RECORD = "INSERT INTO main_products (product_id, product_name, product_type) VALUES (?, ?, ?);";

    public static final String UPDATE_PRODUCT_RECORD = "UPDATE main_products SET product_name = ?, product_type = ? WHERE product_id = ?;";

    public static final String DELETE_PRODUCT_RECORD = "DELETE FROM main_products WHERE product_id = ?;";

    /* -----# Specification queries section #----- */
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

    public static final String ADD_SPEC_RECORD = "INSERT INTO product_spec (source_product_id, target_product_id, quantity) VALUES (?, ?, ?);";

    public static final String UPDATE_SPEC_RECORD = "UPDATE product_spec SET quantity = ? WHERE source_product_id = ? AND target_product_id = ?;";

    public static final String DELETE_SPEC_RECORD = "DELETE FROM product_spec WHERE source_product_id = ? AND target_product_id = ?;";

    /* -----# Special queries section #----- */
    public static final String SELECT_ALL_FIRST_LEVEL_NODE_COMPONENTS_BY_ID = "SELECT " +
            "PS.target_product_id AS component_id, " +
            "MP.product_name AS component_name, " +
            "quantity " +
            "FROM product_spec AS PS " +
            "INNER JOIN main_products AS MP " +
            "ON PS.target_product_id = MP.product_id " +
            "WHERE PS.source_product_id = ?;";

    public static final String SELECT_ALL_FIRST_LEVEL_USABILITY_COMPONENTS_BY_ID = "SELECT " +
            "PS.source_product_id AS component_id, " +
            "MP.product_name AS component_name, " +
            "quantity " +
            "FROM product_spec AS PS " +
            "INNER JOIN main_products AS MP " +
            "ON PS.source_product_id = MP.product_id " +
            "WHERE PS.target_product_id = ?;";

    /* -----# Structural disentanglement queries section #----- */

    public static final String INSERT_DISENTANGLEMENT_MODEL = "INSERT INTO structural_disentanglement " +
            "(product_id, assembly_id, component_id, quantity, development_level, tree_level)" +
            " VALUES (?, ?, ?, ?, ?, ?);";

    public static final String DELETE_ALL_DISENTANGLEMENT_MODELS = "DELETE FROM structural_disentanglement;";

    public static final String SELECT_TOTAL_DISENTANGLEMENT_MODEL = "SELECT SUB.product_id, SUB.component_id, " +
            "SUB.total_component_quantity, SUB.max_development_level, " +
            "MP.product_type_id, PT.type_name " +
            "FROM ( " +
            "SELECT SD.product_id, SD.component_id, " +
            "SUM(SD.quantity) AS total_component_quantity, " +
            "MAX(SD.development_level) AS max_development_level " +
            "FROM structural_disentanglement AS SD " +
            "GROUP BY SD.product_id, SD.component_id " +
            ") AS SUB " +
            "JOIN main_products AS MP " +
            "ON SUB.component_id = MP.product_id " +
            "JOIN product_types AS PT " +
            "ON MP.product_type_id = PT.type_id " +
            "ORDER BY SUB.product_id, SUB.component_id;";

    public static final String INSERT_USABILITY_MODELS = "INSERT INTO structural_usability " +
            "(product_id, component_id, assembly_id, quantity, development_level, tree_level)" +
            " VALUES (?, ?, ?, ?, ?, ?);";

    public static final String SELECT_STRUCTURAL_USABILITY = "SELECT SD.product_id, SD.component_id, SD.assembly_id, " +
            "SD.quantity, " +
            "SD.development_level, SD.tree_level " +
            "FROM structural_disentanglement AS SD " +
            "ORDER BY SD.product_id, SD.component_id, SD.assembly_id DESC;";

    public static final String DELETE_ALL_STRUCTURAL_USABILITY_RECORDS = "DELETE FROM structural_usability;";

    public static final String SELECT_TOTAL_USABILITY_RECORDS = "SELECT SUB.product_id, SUB.component_id, " +
            "SUB.total_component_quantity, SUB.max_development_level, " +
            "MP.product_type_id, PT.type_name " +
            "FROM ( " +
            "SELECT SU.product_id, SU.component_id, " +
            "SUM(SU.quantity) AS total_component_quantity, " +
            "MAX(SU.development_level) AS max_development_level " +
            "FROM structural_usability AS SU  " +
            "GROUP BY SU.product_id, SU.component_id " +
            ") AS SUB " +
            "JOIN main_products AS MP " +
            "ON SUB.component_id = MP.product_id " +
            "JOIN product_types AS PT " +
            "ON MP.product_type_id = PT.type_id " +
            "ORDER BY SUB.product_id, SUB.component_id;";
}
