CREATE TABLE IF NOT EXISTS tenant
(
    id SERIAL PRIMARY KEY,
    data_cadastro date,
    email VARCHAR(255) NOT NULL,
    nome_cliente VARCHAR(255) NOT NULL,
    schema_name VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL
)

