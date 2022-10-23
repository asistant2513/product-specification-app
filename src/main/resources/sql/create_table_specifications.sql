CREATE TABLE IF NOT EXISTS product_spec (
    source_product_id INTEGER NOT NULL CHECK (source_product_id != target_product_id),
    target_product_id INTEGER NOT NULL CHECK (target_product_id != source_product_id),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    PRIMARY KEY (source_product_id, target_product_id),
    FOREIGN KEY (source_product_id)
        REFERENCES products (product_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    FOREIGN KEY (target_product_id)
        REFERENCES products (product_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);