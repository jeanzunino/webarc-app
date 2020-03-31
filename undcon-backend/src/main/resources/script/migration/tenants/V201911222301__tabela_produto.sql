CREATE TABLE IF NOT EXISTS produto
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria_produto_id bigint NOT NULL, 
    estoque bigint,
    estoque_minimo bigint,
    preco_compra double precision,
    preco_venda double precision,
    unidade VARCHAR(255) NOT NULL,
    CONSTRAINT fk_produto_categoria_produto_id FOREIGN KEY (categoria_produto_id)
        REFERENCES categoria_produto (id)
);