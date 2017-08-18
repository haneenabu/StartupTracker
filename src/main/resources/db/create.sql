SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS event(
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR
);