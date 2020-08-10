INSERT INTO permissao  (nome)
SELECT 'ADMIN' 
WHERE not exists (select * from permissao);

UPDATE usuario SET permissao_id = 1 WHERE permissao_id is null;