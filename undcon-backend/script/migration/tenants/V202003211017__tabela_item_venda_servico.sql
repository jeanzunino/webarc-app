CREATE TABLE item_venda
(
    id SERIAL PRIMARY KEY,
    produto_id int NOT NULL,
    venda_id int NOT NULL,
    valor_unitario numeric DEFAULT 9.99,
    quantidade bigint precision,
    CONSTRAINT fk_venda_cliente_id FOREIGN KEY (cliente_id)
        REFERENCES cliente (id)
);