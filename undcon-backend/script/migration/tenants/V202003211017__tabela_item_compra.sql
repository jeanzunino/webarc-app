CREATE TABLE item_compra
(
    id SERIAL PRIMARY KEY,
    produto_id int NOT NULL,
    compra_id int NOT NULL,
    valor numeric DEFAULT 9.99,
    quantidade bigint precision,
    CONSTRAINT fk_compra_fornecedor_id FOREIGN KEY (forncendor_id)
        REFERENCES cliente (id)
);