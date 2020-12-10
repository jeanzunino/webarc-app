DROP TABLE IF EXISTS item_compra_servico;

CREATE TABLE IF NOT EXISTS item_compra_servico
(
    id SERIAL PRIMARY KEY,
    servico_id int NOT NULL,
    compra_id int NOT NULL,
    usuario_id int NOT NULL,
    valor_unitario numeric DEFAULT 9.99,
    quantidade bigint,
    CONSTRAINT fk_item_compra_servico_servico_id FOREIGN KEY (servico_id)
        REFERENCES servico (id),
    CONSTRAINT fk_item_compra_servico_compra_id FOREIGN KEY (compra_id)
        REFERENCES compra (id),
     CONSTRAINT fk_item_compra_servico_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
)