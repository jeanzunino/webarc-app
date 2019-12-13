CREATE TABLE usuario
(
	id SERIAL PRIMARY KEY,
	login VARCHAR(255) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	empregado_id bigint NOT NULL,
    CONSTRAINT fk_usuario_empregado_id FOREIGN KEY (empregado_id)
        REFERENCES empregado (id)
);
