CREATE ROLE binding_group WITH SUPERUSER;
GRANT binding_group TO "skipdata";
CREATE DATABASE skipdata WITH OWNER "skipdata";