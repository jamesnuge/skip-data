CREATE ROLE binding_group WITH SUPERUSER;
GRANT binding_group TO "fantasy.league";
CREATE DATABASE fantasy WITH OWNER "fantasy.league";