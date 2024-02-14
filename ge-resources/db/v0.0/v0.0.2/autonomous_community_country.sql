create sequence dbo.seq_autonomous_community_country
    as integer
    minvalue 100;

alter sequence dbo.seq_autonomous_community_country owner to gesco;




create table autonomous_community_country
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

alter table autonomous_community_country
    owner to gesco;

INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (117, 'RI', 'LA RIOJA', 'RI', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (107, 'CM', 'CASTILLA-LA MANCHA', 'CM', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (113, 'ML', 'MELILLA', 'ML', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (111, 'GA', 'GALICIA', 'GA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (108, 'CN', 'CANTABRIA', 'CN', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (110, 'EX', 'EXTREMADURA', 'EX', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (105, 'CE', 'CEUTA', 'CE', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (100, 'AN', 'ANDALUCIA', 'AN', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (102, 'AS', 'PRINCIPADO DE ASTURIAS', 'AS', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (101, 'AR', 'ARAGON', 'AR', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (116, 'PV', 'PAIS VASCO', 'PV', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (118, 'VA', 'COMUNITAT VALENCIANA', 'VA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (104, 'CA', 'CANARIAS', 'CA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (112, 'MA', 'COMUNIDAD DE MADRID', 'MA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (109, 'CT', 'CATALUNYA', 'CT', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (106, 'CL', 'CASTILLA Y LEON', 'CL', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (114, 'MU', 'REGION DE MURCIA', 'MU', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (115, 'NA', 'NAVARRA', 'NA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);
INSERT INTO dbo.autonomous_community_country (id, code, name, country_code, country_id, created_at, updated_at, created_by, updated_by, id_state) VALUES (103, 'BA', 'BALEARS', 'BA', 301, '2024-02-13 16:44:10.190110', '2024-02-13 16:44:10.190110', null, null, 1);


