INSERT INTO empregado(nome) VALUES ('Admin');

DELETE FROM usuario where login = 'admin@' || current_schema ();

-- Insere o usuário admin para tenant cliente1 com senha 12345678
INSERT INTO usuario(login, empregado_id, senha)
	VALUES ('admin@' || current_schema (), 1, 'EF797C8118F02DFB649607DD5D3F8C7623048C9C063D532CC95C5ED7A898A64F');