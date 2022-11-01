CREATE TABLE IF NOT EXISTS total_disentanglement (
    assembly_id VARCHAR(32) NOT NULL,
    component_id VARCHAR(32) NOT NULL,
    total_quantity INTEGER NOT NULL CHECK (total_quantity > 0),
    max_development_level INTEGER NOT NULL CHECK (max_development_level > 0),
    component_type_id INTEGER NOT NULL
);