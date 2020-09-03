DROP TABLE IF EXISTS item_compra_produto;

CREATE TABLE IF NOT EXISTS item_compra_produto
(
    id SERIAL PRIMARY KEY,
    produto_id int NOT NULL,
    compra_id int NOT NULL,
    usuario_id int NOT NULL,
    valor_unitario numeric DEFAULT 9.99,
    quantidade bigint,
    CONSTRAINT fk_item_compra_produto_produto_id FOREIGN KEY (produto_id)
        REFERENCES produto (id),
    CONSTRAINT fk_item_compra_produto_compra_id FOREIGN KEY (compra_id)
        REFERENCES compra (id),
    CONSTRAINT fk_item_compra_produto_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
)