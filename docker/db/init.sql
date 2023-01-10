CREATE ROLE binding_group WITH SUPERUSER;
CREATE USER "skipdata" PASSWORD 'password';
GRANT binding_group TO "skipdata";
CREATE DATABASE skipdata WITH OWNER "skipdata";
