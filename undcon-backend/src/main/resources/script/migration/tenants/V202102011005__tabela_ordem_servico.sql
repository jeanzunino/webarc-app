DROP TABLE IF EXISTS ordem_servico;

CREATE TABLE IF NOT EXISTS ordem_servico
(
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    data_inicial date,
    data_final date,
    data_cadastro date NOT NULL,
    defeito VARCHAR(255) NOT NULL,
    garantia VARCHAR(255) NOT NULL,
    laudo_tecnico VARCHAR(255) NOT NULL,
    observacoes VARCHAR(255) NOT NULL,
    cliente_id int NOT NULL,
    usuario_id int NOT NULL,
    status_pagamento int NOT NULL,
    status_os int NOT NULL
);