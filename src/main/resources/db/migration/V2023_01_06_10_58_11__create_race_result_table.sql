SET ROLE binding_group;
CREATE TABLE race_result(
    id            BIGSERIAL PRIMARY KEY,
    datetime      date NOT NULL,
    result        BIGINT NOT NULL,
    location      VARCHAR(255) NOT NULL,
    temperature   BIGINT NOT NULL,
    humidity      BIGINT NOT NULL
)