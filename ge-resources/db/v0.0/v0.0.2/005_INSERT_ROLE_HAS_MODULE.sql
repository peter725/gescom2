INSERT INTO public.role (id, name, enable, visible) VALUES (1, 'Admin', true, true);
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (1, 1, 1, 1, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (2, 2, 1, 1, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (3, 3, 1, 1, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (4, 4, 1, 1, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (5, 5, 1, 1, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (6, 1, 1, 2, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (7, 2, 1, 2, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (8, 3, 1, 2, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (9, 4, 1, 2, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (10, 5, 1, 2, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (11, 1, 1, 3, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (12, 2, 1, 3, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (13, 3, 1, 3, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (14, 4, 1, 3, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (15, 5, 1, 3, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (16, 1, 1, 4, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (17, 2, 1, 4, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (18, 3, 1, 4, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (19, 4, 1, 4, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (20, 5, 1, 4, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (21, 1, 1, 5, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (22, 2, 1, 5, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (23, 3, 1, 5, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (24, 4, 1, 5, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (25, 5, 1, 5, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (26, 1, 1, 7, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (27, 2, 1, 7, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (28, 3, 1, 7, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (29, 4, 1, 7, 'ALL');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (30, 5, 1, 7, 'ALL');

insert into login_has_role (login_id, role_id) values (1,1);

INSERT INTO public.role (id, name, enable, visible) VALUES (2, 'Solicitante', true, true);

INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (31, 1, 2, 6, 'ME');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (32, 2, 2, 6, 'ME');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (33, 3, 2, 6, 'ME');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (34, 4, 2, 6, 'ME');
INSERT INTO public.role_has_module (id, permission_id, role_id, module_id, scope) VALUES (35, 5, 2, 6, 'ME');

insert into login_has_role (login_id, role_id) values (2,2);

SELECT setval('role_id_seq', (select max(id) from role), true);
SELECT setval('role_has_module_id_seq', (select max(id) from role_has_module), true);