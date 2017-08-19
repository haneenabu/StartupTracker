SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS attendee(
    id int PRIMARY KEY auto_increment,
    attendeeName VARCHAR,
    age INTEGER,
    eventId INTEGER
);
CREATE TABLE IF NOT EXISTS event(
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    description VARCHAR
);