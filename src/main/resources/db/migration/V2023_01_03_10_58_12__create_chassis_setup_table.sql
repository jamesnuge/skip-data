SET ROLE binding_group;

CREATE TABLE chassis_setup(
    id                       BIGSERIAL PRIMARY KEY,
    name                     VARCHAR(255) NOT NULL,
    front_spread             BIGINT NOT NULL,
    rear_spread              BIGINT NOT NULL,
    front_crossmember_height BIGINT NOT NULL,
    rear_crossmember_height  BIGINT NOT NULL,
    rear_steer               BIGINT NOT NULL,
    preload                  BIGINT NOT NULL
)