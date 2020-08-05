DROP TABLE IF EXISTS receita;

CREATE TABLE IF NOT EXISTS receita
(
    id SERIAL PRIMARY KEY,
    baixado boolean NOT NULL,
    data_vencimento date NOT NULL,
    data_pagamento date,
    descricao VARCHAR(255) NOT NULL,
    forma_pgto integer NOT NULL,
    valor numeric DEFAULT 9.99,
    cliente_id bigint NOT NULL,
    venda_id bigint,
    CONSTRAINT fk_receita_cliente_id FOREIGN KEY (cliente_id)
        REFERENCES cliente(id),
    CONSTRAINT fk_receita_venda_id FOREIGN KEY (venda_id)
        REFERENCES venda (id)
)