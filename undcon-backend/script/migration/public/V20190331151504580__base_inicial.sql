CREATE TABLE public.clientes
(
    id SERIAL PRIMARY KEY,
    data_cadastro date,
    email character varying(255) COLLATE pg_catalog."default",
    nome_cliente character varying(255) COLLATE pg_catalog."default",
    schema_name character varying(255) COLLATE pg_catalog."default",
    telefone character varying(255) COLLATE pg_catalog."default"
)

