CREATE TYPE public.permission_scope_type AS ENUM ('ME','REGION','ALL');

alter table role_has_module
    alter column scope type permission_scope_type using scope::permission_scope_type;