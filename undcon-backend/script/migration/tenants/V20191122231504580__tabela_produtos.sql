CREATE TABLE produtos
(
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    estoque integer,
    estoque_minimo integer,
    preco_compra double precision,
    preco_venda double precision,
    unidade VARCHAR(255) NOT NULL
);