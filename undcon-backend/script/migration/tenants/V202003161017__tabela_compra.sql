CREATE TABLE compra
(
    id SERIAL PRIMARY KEY,
    fornecedor_id int NOT NULL,
    usuario_id int NOT NULL,
    faturado boolean NOT NULL,
    data_compra date NOT NULL,
    status int NOT NULL,
    CONSTRAINT fk_compra_fornecedor_id FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedor (id),
    CONSTRAINT fk_compra_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id)
);