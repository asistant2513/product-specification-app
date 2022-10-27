-- Setup product types table
INSERT INTO product_types (type_id, type_name) VALUES
    (1, 'Виріб'),
    (2, 'Вузол (агрегат, збірка)'),
    (3, 'Деталь власного виробництва'),
    (4, 'Придбаний (комплектувальний) предмет');

-- Setup products table
INSERT INTO main_products (product_id, product_name, product_type_id) VALUES
    ('A', 'Стабілізатор Ст-36', 1),
    ('B', 'Корпус стабілізатора', 2),
    ('C', 'Блок стабілізації', 2),
    ('D', 'Каркас', 3),
    ('E', 'Шасі', 3),
    ('F', 'Гайка М6', 3),
    ('G', 'Гвинт М6х25', 3),
    ('H', 'Шайба d7.0', 3),
    ('I', 'Тріод 2в, 0.05а', 4),
    ('J', 'Опір 120х0.5', 4),
    ('K', 'Конденсатор 2А-250-1,0', 4),
    ('L', 'Плата з двома П-образними клемами ', 4),
    ('M', 'Стабілізатор Ст-18', 1);

-- Setup specification table
INSERT INTO product_spec (source_product_id, target_product_id, quantity) VALUES
    ('A', 'B', 1),
    ('A', 'C', 1),
    ('A', 'F', 6),
    ('A', 'G', 6),
    ('A', 'H', 6),
    ('B', 'C', 2),
    ('B', 'D', 1),
    ('B', 'F', 6),
    ('B', 'G', 6),
    ('B', 'H', 6),
    ('C', 'E', 1),
    ('C', 'I', 3),
    ('C', 'J', 2),
    ('C', 'K', 1),
    ('C', 'L', 4),
    ('M', 'C', 1),
    ('M', 'D', 1),
    ('M', 'F', 4),
    ('M', 'G', 4),
    ('M', 'H', 4);