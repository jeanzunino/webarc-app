CREATE TABLE categoria_produto
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria_pai_id bigint,
    CONSTRAINT fk_categoria_produto_categoria_pai_id FOREIGN KEY (categoria_pai_id)
        REFERENCES categoria_produto(id)
)