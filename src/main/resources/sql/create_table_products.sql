CREATE TABLE IF NOT EXISTS main_products (
    product_id VARCHAR(32) PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_type_id INTEGER NOT NULL,
    FOREIGN KEY (product_type_id)
        REFERENCES product_types (type_id)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);