CREATE TABLE IF NOT EXISTS item_compra_produto
(
    id SERIAL PRIMARY KEY,
    produto_id int NOT NULL,
    compra_id int NOT NULL,
    valor numeric DEFAULT 9.99,
    quantidade bigint,
    CONSTRAINT fk_item_compra_produto_produto_id FOREIGN KEY (produto_id)
        REFERENCES produto (id),
    CONSTRAINT fk_item_compra_produto_compra_id FOREIGN KEY (compra_id)
        REFERENCES compra (id)
);