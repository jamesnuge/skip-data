SET ROLE binding_group;
CREATE TABLE user_profile(
    id            BIGSERIAL PRIMARY KEY,
    email         TEXT NOT NULL,
    password      TEXT NOT NULL,
    role          TEXT NOT NULL,
    display_name  TEXT,
    first_name    TEXT,
    last_name     TEXT,
    CONSTRAINT email_user UNIQUE (email)
)