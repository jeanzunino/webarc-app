ALTER TABLE receita ADD	COLUMN IF NOT EXISTS status int;
ALTER TABLE despesa ADD	COLUMN IF NOT EXISTS status int;

update receita set status = 0 WHERE baixado = false;
update despesa set status = 0 WHERE baixado = true;

ALTER TABLE receita DROP COLUMN IF EXISTS baixado;
ALTER TABLE despesa DROP COLUMN IF EXISTS baixado;