ALTER TABLE usuario ADD	column IF NOT EXISTS permissao_id bigint;

ALTER TABLE usuario DROP CONSTRAINT IF EXISTS fk_usuario_permissao_id;
ALTER TABLE usuario ADD CONSTRAINT fk_usuario_permissao_id FOREIGN KEY (permissao_id)
        REFERENCES permissao(id);