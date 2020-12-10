DROP TABLE IF EXISTS despesa;

CREATE TABLE IF NOT EXISTS despesa
(
    id SERIAL PRIMARY KEY,
    data_vencimento date NOT NULL,
    data_pagamento date,
    descricao VARCHAR(255) NOT NULL,
    status int,
    forma_pgto integer NOT NULL,
    valor numeric DEFAULT 9.99,
    compra_id bigint,
    fornecedor_id bigint NOT NULL,
    CONSTRAINT fk_despesa_fornecedor_id FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedor(id),
    CONSTRAINT fk_despesa_compra_id FOREIGN KEY (compra_id)
        REFERENCES compra (id)
)