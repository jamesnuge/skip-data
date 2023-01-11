SET ROLE binding_group;

CREATE TABLE race_result(
    id                     BIGSERIAL PRIMARY KEY,
    datetime               date NOT NULL,
    location               VARCHAR(255) NOT NULL,
    temperature            BIGINT NOT NULL,
    humidity               BIGINT NOT NULL,
    altitude               BIGINT NOT NULL,
    track_temperature      BIGINT NOT NULL,
    trackmeter             BIGINT NOT NULL,
    sixty_feet_time        NUMERIC NOT NULL,
    three_thirty_feet_time NUMERIC NOT NULL,
    six_sixty_feet_time    NUMERIC NOT NULL,
    six_sixty_feet_speed   NUMERIC NOT NULL,
    quarter_mile_time      NUMERIC NOT NULL,
    quarter_mile_speed     NUMERIC NOT NULL
)