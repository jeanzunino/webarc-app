ALTER TABLE usuario ADD	column IF NOT EXISTS resetar_senha boolean;
UPDATE usuario SET resetar_senha = false;