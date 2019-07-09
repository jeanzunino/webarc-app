CREATE SEQUENCE public.clientes_id_clientes_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.clientes_id_clientes_seq
    OWNER TO postgres;

CREATE TABLE public.clientes
(
    id bigint NOT NULL DEFAULT nextval('clientes_id_clientes_seq'::regclass),
    data_cadastro date,
    email character varying(255) COLLATE pg_catalog."default",
    nome_cliente character varying(255) COLLATE pg_catalog."default",
    schema_name character varying(255) COLLATE pg_catalog."default",
    telefone character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT clientes_pkey PRIMARY KEY (id)
)

