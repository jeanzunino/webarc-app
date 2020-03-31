CREATE TABLE IF NOT EXISTS permissao_item
(
    id SERIAL PRIMARY KEY,
    permissao_id int NOT NULL,
    tipo int NOT NULL,
    CONSTRAINT fk_permissao_item_permissao_id FOREIGN KEY (permissao_id)
        REFERENCES permissao (id)
);