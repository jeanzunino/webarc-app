CREATE TABLE IF NOT EXISTS despesa
(
    id SERIAL PRIMARY KEY,
    baixado boolean NOT NULL,
    data_pagamento date NOT NULL,
    data_vencimento date NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    forma_pgto integer NOT NULL,
    valor double precision,
    fornecedor_id bigint NOT NULL,
    CONSTRAINT fk_despesa_fornecedor_id FOREIGN KEY (fornecedor_id)
        REFERENCES fornecedor(id)
)