CREATE TABLE IF NOT EXISTS structural_usability (
    product_id VARCHAR(32) NOT NULL,
    component_id VARCHAR(32) NOT NULL,
    assembly_id VARCHAR(32) NOT NULL,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    development_level INTEGER NOT NULL CHECK (development_level > 0),
    tree_level VARCHAR(128) NOT NULL
);