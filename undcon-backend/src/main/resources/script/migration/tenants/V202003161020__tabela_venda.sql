CREATE TABLE IF NOT EXISTS venda
(
    id SERIAL PRIMARY KEY,
    cliente_id int NOT NULL,
    usuario_id int NOT NULL,
    vendedor_id int NOT NULL,
    faturado boolean NOT NULL,
    data_venda date NOT NULL,
    status int NOT NULL,
    CONSTRAINT fk_venda_cliente_id FOREIGN KEY (cliente_id)
        REFERENCES cliente (id),
    CONSTRAINT fk_venda_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id),
    CONSTRAINT fk_venda_vendedor_id FOREIGN KEY (vendedor_id)
        REFERENCES empregado (id)
);