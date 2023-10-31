INSERT INTO USER(id, name, first_surname, second_surname, nif, position, area_responsabilidad, role_id, phone_id, email_id, national_authority_id, created_at, updated_at, created_by, updated_by, id_state)
VALUES(1, 'jhon', 'rojas', 'silva', '123456789', 'position', 'area_respo', 1, 0, 0, 1, '2023-06-10', '2023-06-10', 1, 1, 1);

INSERT INTO USER(id, name, first_surname, second_surname, nif, position, area_responsabilidad, role_id, phone_id, email_id, national_authority_id, created_at, updated_at, created_by, updated_by, id_state)
VALUES(2, 'juan', 'perez', 'rodriguez', '123456788', 'position', 'area_respo', 1, 0, 0, 1, '2023-06-10', '2023-06-10', 1, 1, 1);

INSERT INTO USER(id, name, first_surname, second_surname, nif, position, area_responsabilidad, role_id, phone_id, email_id, national_authority_id, created_at, updated_at, created_by, updated_by, id_state)
VALUES(3, 'luis', 'gomez', 'arevalo', '123456787', 'position', 'area_respo', 1, 0, 0, 1, '2023-06-10', '2023-06-10', 1, 1, 1);

INSERT INTO USER(id, name, first_surname, second_surname, nif, position, area_responsabilidad, role_id, phone_id, email_id, national_authority_id, created_at, updated_at, created_by, updated_by, id_state)
VALUES(4, 'denis', 'perez', 'rodriguez', '123456786', 'position', 'area_respo', 1, 0, 0, 1, '2023-06-10', '2023-06-10', 1, 1, 1);

ALTER SEQUENCE seq_users RESTART WITH 5;