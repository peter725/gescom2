
create table login
(
    id          bigserial not null
        constraint login_pk
            primary key,
    username    varchar(100),
    password    varchar(100),
	enable boolean default true not null,
    last_access timestamp
);

create index login_username_index
    on login (username);

alter table users
    add login_id bigint;
	
create index users_login_id_index
    on users (login_id);

alter table users
    add constraint users_login_id_fk
        foreign key (login_id) references login;


	
	create table permission
(
    id        bigserial               not null
        constraint permission_pk
            primary key,
    create_at timestamp default now() not null,
    name      varchar(100)            not null,
    code   varchar(20)             not null,
    visible   boolean   default true  not null
);

create unique index permission_acronym_uindex
    on permission (code);

create index permission_visible_acronym_index
    on permission (visible, code);

create index permission_visible_index
    on permission (visible);


create table module
(
    id        bigserial
        constraint module_pk
            primary key,
    create_at timestamp default now() not null,
    name      varchar(100)            not null,
    code   varchar(20)  not null,
    visible   boolean   default true  not null
);


create unique index module_acronym_uindex
    on module (code);

create index module_visible_acronym_index
    on module (visible, code);

create index module_visible_index
    on module (visible);

create table role
(
    id        bigserial               not null
        constraint role_pk
            primary key,
    create_at timestamp default now() not null,
    update_at timestamp,
    name      varchar(100)            not null,
    enable    boolean   default true  not null,
    visible   boolean   default true  not null
);

create unique index role_name_uindex
    on role (name);

create index role_name_visible_enable_index
    on role (name, visible, enable);

create index role_visible_enable_index
    on role (visible, enable);


create table role_has_module
(    id        bigserial               not null
        constraint role_has_module_pk
            primary key,
    permission_id bigint not null
        constraint role_has_module_permission_id_fk
            references permission,
    role_id    bigint not null
        constraint role_has_module_role_id_fk
            references role,
    module_id  bigint not null   
        constraint role_has_module_module_id_fk
            references module,
		  scope   varchar(10)    not null
);

create table login_has_role
(
    login_id bigint not null
        constraint login_has_role_login_id_fk
            references login,
    role_id  bigint not null
        constraint login_has_role_role__fk
            references role,
    constraint login_has_role_pk
        unique (login_id, role_id)
);

insert into login (id, username, password, enable)
values (1,'12345678A','$2a$10$4jtrDldJraFxQe/KnzXJBez.nTutTpOJRRBD54SPC2F.9Oa1BIKGK',true);
insert into login (id, username, password, enable)
values (2,'87654321A','$2a$10$4jtrDldJraFxQe/KnzXJBez.nTutTpOJRRBD54SPC2F.9Oa1BIKGK',true);

update users set  login_id=1  where id=1;
update users set  login_id=2  where id=2;