CREATE TABLE IF NOT EXISTS public.vendedor_sistema
(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	telefone VARCHAR(50)
);

INSERT INTO public.vendedor_sistema (nome, telefone) VALUES ('Jean Zunino', '991681617');
INSERT INTO public.vendedor_sistema (nome, telefone) VALUES ('Gabriel Bernardi', '996141769');

ALTER TABLE public.tenant ADD column IF NOT EXISTS vendedor_id int;

update public.tenant set vendedor_id = 1;

ALTER TABLE public.tenant ADD CONSTRAINT fk_tenant_vendedor_id FOREIGN KEY (vendedor_id)
        REFERENCES public.vendedor_sistema (id);
