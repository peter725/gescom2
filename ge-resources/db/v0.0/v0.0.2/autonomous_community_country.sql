create sequence dbo.seq_autonomous_community_country
    as integer
    minvalue 20;


create table dbo.autonomous_community_country
(
    id           bigint default nextval('dbo.seq_autonomous_community_country'::regclass) not null
        constraint autonomous_community_country_pk
            primary key,
    code         varchar(2),
    name         varchar(50),
    country_code varchar(3),
    country_id   bigint,
    created_at   timestamp,
    updated_at   timestamp,
    created_by   bigint,
    updated_by   bigint,
    id_state     integer
);

alter table dbo.autonomous_community_country
    owner to gesco;

INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (19, 'NA', 'NAVARRA', 'NA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (18, 'MU', 'REGION DE MURCIA', 'MU', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (2, 'MA', 'COMUNIDAD DE MADRID', 'MA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (14, 'VA', 'COMUNITAT VALENCIANA', 'VA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (15, 'BA', 'BALEARS', 'BA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (7, 'CL', 'CASTILLA Y LEON', 'CL', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (6, 'CM', 'CASTILLA-LA MANCHA', 'CM', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (8, 'AS', 'PRINCIPADO DE ASTURIAS', 'AS', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (17, 'ML', 'MELILLA', 'ML', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (13, 'CE', 'CEUTA', 'CE', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (12, 'CA', 'CANARIAS', 'CA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (11, 'AR', 'ARAGON', 'AR', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (10, 'CT', 'CATALUNYA', 'CT', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (9, 'CN', 'CANTABRIA', 'CN', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (5, 'EX', 'EXTREMADURA', 'EX', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (4, 'GA', 'GALICIA', 'GA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (3, 'AN', 'ANDALUCIA', 'AN', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (1, 'PV', 'PAIS VASCO', 'PV', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (16, 'RI', 'LA RIOJA', 'RI', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);




