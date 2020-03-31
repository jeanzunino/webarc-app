CREATE TABLE IF NOT EXISTS item_venda_produto
(
    id SERIAL PRIMARY KEY,
    produto_id int NOT NULL,
    venda_id int NOT NULL,
    valor_unitario numeric DEFAULT 9.99,
    quantidade bigint,
    CONSTRAINT fk_item_venda_produto_produto_id FOREIGN KEY (produto_id)
        REFERENCES produto (id),
    CONSTRAINT fk_item_venda_produto_venda_id FOREIGN KEY (venda_id)
        REFERENCES venda (id)
);