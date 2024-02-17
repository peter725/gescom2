create table field
(
    id          bigint default nextval('dbo.seq_field'::regclass) not null,
    description varchar(50),
    type        varchar(10),
    created_at  timestamp,
    updated_at  timestamp,
    created_by  bigint,
    updated_by  bigint,
    id_state    integer
);

alter table field
    owner to gesco;

INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (27, 'Riesgos mecánicos', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (9, 'Construcción', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (18, 'Rigidez dieléctrica', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (2, 'Contenido efectivo', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (24, 'Funcionamiento anormal', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (11, 'Líneas de fuga', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (29, 'Símbolo gráfico de recogida selectiva', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (26, 'resistencia mecánica', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (3, 'Plomo', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (17, 'Resistencia de aislamiento', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (28, 'Rigidez dieléctrica a temperatura de régimen', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (22, 'Conductores internos', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (16, 'Resistencia al fuego', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (6, 'Bornes', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (12, 'Marcado "CE"', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (8, 'Conexión red y cables', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (25, 'Potencia e intensidad', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (30, 'Código de Barras', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (5, 'Marcas e indicaciones', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (13, 'Protección acceso part. activas', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (19, 'uuuuu', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (4, 'Ácidos grasos', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (15, 'Resistencia al calor', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (10, 'Distancias en el aire', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (1, 'Tipo de Producto', 'varchar', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (23, 'Distancias a través del aislamiento', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (7, 'Clasificación', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (14, 'Resistencia a la humedad', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (20, 'Símbolo gráfico de recogida selectiva', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
INSERT INTO dbo.field (id, description, type, created_at, updated_at, created_by, updated_by, id_state) VALUES (21, 'Calentamientos', 'int', '2024-02-15 11:49:45.000000', '2024-02-15 11:49:45.000000', null, null, 1);
