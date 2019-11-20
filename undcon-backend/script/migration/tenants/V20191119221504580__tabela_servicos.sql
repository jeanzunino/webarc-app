CREATE TABLE servicos
(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(80) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	preco double precision NOT NULL
);
