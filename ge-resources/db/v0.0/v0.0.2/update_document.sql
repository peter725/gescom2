alter table public.document_type
    add id_state integer not null;
	
alter table public.document
    rename column create_at to created_at;
	
ALTER SEQUENCE document_type_id_seq RESTART WITH 1;
	
insert into public.document_type values (nextval('document_type_id_seq'::regclass), localtimestamp, localtimestamp, 'Bibliotca', true, 1);
insert into public.document_type values (nextval('document_type_id_seq'::regclass), localtimestamp, localtimestamp, 'Documento de planificacion', true, 1);
insert into public.document_type values (nextval('document_type_id_seq'::regclass), localtimestamp, localtimestamp, 'Ficha de transparencia', true, 1);