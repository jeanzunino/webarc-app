CREATE TABLE usuarios
(
	id SERIAL PRIMARY KEY,
	login VARCHAR(255) NOT NULL,
	empregado_id bigint NOT NULL,
    CONSTRAINT fk_usuarios_empregado_id FOREIGN KEY (empregado_id)
        REFERENCES empregados (id)
);
