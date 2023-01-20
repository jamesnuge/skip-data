SET ROLE binding_group;

CREATE TABLE location(
    id                     BIGSERIAL PRIMARY KEY,
    name                   VARCHAR(255) NOT NULL,
    altitude               BIGINT NOT NULL
)