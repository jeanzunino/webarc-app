DROP TABLE IF EXISTS item_venda;
DROP TABLE IF EXISTS item_venda_servico;

DROP TABLE IF EXISTS item_venda_produto;

CREATE TABLE IF NOT EXISTS item_venda_produto
(
    id SERIAL PRIMARY KEY,
    produto_id int NOT NULL,
    venda_id int NOT NULL,
    empregado_id int NOT NULL,
    usuario_id int NOT NULL,
    valor_unitario numeric DEFAULT 9.99,
    quantidade bigint,
    CONSTRAINT fk_item_venda_produto_produto_id FOREIGN KEY (produto_id)
        REFERENCES produto (id),
    CONSTRAINT fk_item_venda_produto_venda_id FOREIGN KEY (venda_id)
        REFERENCES venda (id),
    CONSTRAINT fk_item_venda_produto_empregado_id FOREIGN KEY (empregado_id)
        REFERENCES empregado (id),
        CONSTRAINT fk_item_venda_produto_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
);

CREATE TABLE IF NOT EXISTS item_venda_servico
(
    id SERIAL PRIMARY KEY,
    servico_id int NOT NULL,
    venda_id int NOT NULL,
    empregado_id int NOT NULL,
    usuario_id int NOT NULL,
    valor_unitario numeric DEFAULT 9.99,
    quantidade bigint,
    CONSTRAINT fk_item_venda_servico_servico_id FOREIGN KEY (servico_id)
        REFERENCES servico (id),
    CONSTRAINT fk_item_venda_servico_venda_id FOREIGN KEY (venda_id)
        REFERENCES venda (id),
    CONSTRAINT fk_item_venda_servico_empregado_id FOREIGN KEY (empregado_id)
        REFERENCES empregado (id),
        CONSTRAINT fk_item_venda_servico_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
);