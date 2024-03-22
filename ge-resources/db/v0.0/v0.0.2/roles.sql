-- Modules
INSERT INTO "dbo"."module" VALUES (8, '2024-01-15 18:03:16.142921', 'Campañas', 'campaign', true);
INSERT INTO "dbo"."module" VALUES (9, '2024-01-15 18:03:16.142921', 'Propuestas', 'approach', true);
SELECT pg_catalog.setval('"dbo"."module_id_seq"', 10, true);

-- Roles
INSERT INTO "dbo"."role" (create_at,update_at,name,"enable",visible,id_state) VALUES
	 ('2024-01-15 18:03:16.142921','2024-01-15 18:03:16.142921','DGC',true,true,1),
	 ('2024-01-15 18:03:16.142921','2024-01-15 18:03:16.142921','CCAA',true,true,1),
	 ('2024-01-15 18:03:16.142921','2024-01-15 18:03:16.142921','ADMINISTRADOR DE ÁMBITO',true,true,1),
	 ('2024-01-15 18:03:16.142921','2024-01-15 18:03:16.142921','CICC',true,true,1);

-- DGC 
INSERT INTO "dbo"."role_has_module" VALUES (1, 1, 1, 8, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (2, 2, 1, 8, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (3, 4, 1, 8, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (4, 1, 1, 4, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (5, 2, 1, 4, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (6, 4, 1, 4, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (7, 1, 1, 9, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (8, 2, 1, 9, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (9, 4, 1, 9, 'ALL');
-- CCAA 
INSERT INTO "dbo"."role_has_module" VALUES (10, 1, 2, 4, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (11, 1, 2, 9, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (12, 2, 2, 9, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (13, 1, 2, 8, 'ALL');
-- Administrador 
INSERT INTO "dbo"."role_has_module" VALUES (14, 1, 3, 4, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (15, 2, 3, 4, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (16, 4, 3, 4, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (17, 1, 3, 9, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (18, 2, 3, 9, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (19, 4, 3, 9, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (20, 1, 3, 8, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (21, 2, 3, 8, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (22, 4, 3, 8, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (23, 1, 3, 3, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (24, 2, 3, 3, 'ALL');
INSERT INTO "dbo"."role_has_module" VALUES (25, 4, 3, 3, 'ALL');
SELECT pg_catalog.setval('"dbo"."role_has_module_id_seq"', 26, true);