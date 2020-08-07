CREATE TABLE IF NOT EXISTS cheque_bancario
(
    id SERIAL PRIMARY KEY,
    pessoa_nome VARCHAR(80) NOT NULL,
    pessoa_documento VARCHAR(80) NOT NULL,
    numero_cheque bigint NOT NULL
    
);