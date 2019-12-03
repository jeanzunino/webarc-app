CREATE TABLE receita
(
    id SERIAL PRIMARY KEY,
    baixado boolean NOT NULL,
    data_pagamento date NOT NULL,
    data_vencimento date NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    forma_pgto integer NOT NULL,
    valor double precision,
    cliente_id bigint NOT NULL,
    CONSTRAINT fk_receita_cliente_id FOREIGN KEY (cliente_id)
        REFERENCES cliente(id)
)