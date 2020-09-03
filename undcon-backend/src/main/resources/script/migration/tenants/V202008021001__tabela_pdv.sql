DROP TABLE IF EXISTS pdv;

CREATE TABLE IF NOT EXISTS pdv
(
    id SERIAL PRIMARY KEY,
    usuario_id bigint NOT NULL,
    usuario_responsavel_abertura_id bigint NOT NULL,
    usuario_responsavel_fechamento_id bigint,
    data_abertura date NOT NULL,
    data_fechamento date,
    valor_abertura numeric DEFAULT 9.99,
    valor_fechamento numeric DEFAULT 9.99,
    
    CONSTRAINT fk_pdv_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id),
    CONSTRAINT fk_pdv_usuario_responsavel_abertura_id FOREIGN KEY (usuario_responsavel_abertura_id)
        REFERENCES usuario (id),
    CONSTRAINT fk_pdv_usuario_responsavel_fechamento_id FOREIGN KEY (usuario_responsavel_fechamento_id)
        REFERENCES usuario (id)
    
);