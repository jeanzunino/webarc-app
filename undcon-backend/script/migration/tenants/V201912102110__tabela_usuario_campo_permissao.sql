ALTER TABLE usuario ADD	column permissao_id bigint;
ALTER TABLE usuario ADD CONSTRAINT fk_usuario_permissao_id FOREIGN KEY (permissao_id)
        REFERENCES permissao(id);