CREATE TABLE public.menu_template
(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    template_padrao boolean NOT NULL
);

CREATE TABLE public.menu_template_item
(
	id SERIAL PRIMARY KEY,
    menu_template_id SERIAL PRIMARY KEY,
    tipo_recurso int NOT NULL,
    ordem int NOT NULL,
    CONSTRAINT fk_menu_template_item_menu_template_id FOREIGN KEY (menu_template_id)
        REFERENCES menu_template (id)
);

ALTER TABLE public.tenant ADD	column menu_template_id bigint NOT NULL;
ALTER TABLE public.tenant ADD CONSTRAINT fk_tenant_menu_template_id FOREIGN KEY (menu_template_id) REFERENCES menu_template (id);

