CREATE ROLE binding_group WITH SUPERUSER;
GRANT binding_group TO "skip.data";
CREATE DATABASE fantasy WITH OWNER "skip.data";