-- Database: euclid

-- DROP DATABASE euclid;

CREATE DATABASE euclid_system_management
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Chinese (Simplified)_China.936'
    LC_CTYPE = 'Chinese (Simplified)_China.936'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE euclid_system_management
    IS 'euclid_system_management for dev';