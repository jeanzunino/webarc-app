ALTER TABLE usuario ADD	column IF NOT EXISTS ativo boolean;
UPDATE usuario SET ativo = true;