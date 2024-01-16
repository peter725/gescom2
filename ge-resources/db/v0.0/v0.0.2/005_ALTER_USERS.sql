ALTER TABLE users ADD COLUMN email VARCHAR(100) ;

update users set email = 'jjaa_system@mailinator.com' where id=1;
update users set email = 'jjaa_interno@mailinator.com' where id=2;

alter table users alter column email set not null;

SELECT setval('users_id_seq', (select max(id) from users), true);
SELECT setval('login_id_seq', (select max(id) from users), true);