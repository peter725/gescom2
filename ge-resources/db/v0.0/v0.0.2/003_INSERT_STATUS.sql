alter table arbitration_status
    add code varchar(50);
	
update arbitration_status set code='DRAFT' where id=1;
update arbitration_status set code='SIGNED',name='Firmado' where id=2;
update arbitration_status set code='SENT', name='Enviado' where id=3;

insert into arbitration_status (name, code) values ('Competencia Territorial','TERRITORIAL_COMPETITION');
insert into arbitration_status (name, code) values ('Asignada','ASSIGNED');
insert into arbitration_status (name, code) values ('Admision','ADMISSION');
insert into arbitration_status (name, code) values ('Inadmitida','INADMISSIBLE');
insert into arbitration_status (name, code) values ('Admitida','ADMITTED');

alter table arbitration_status
    alter column code set not null;

