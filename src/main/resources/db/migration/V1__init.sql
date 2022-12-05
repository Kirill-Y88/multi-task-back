DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles(
                           id bigserial PRIMARY KEY,
                           title varchar(30) not null unique
);

DROP TABLE IF EXISTS users;
CREATE TABLE users(
                      id bigserial PRIMARY KEY,
                      login varchar(30) not null unique,
                      password varchar(80) not null,
                      email varchar (80) unique,
                      name varchar(80),
                      role_id integer REFERENCES user_roles(id)
);

DROP TABLE IF EXISTS notes;
CREATE TABLE notes(
                           id bigserial PRIMARY KEY,
                           title varchar(30),
                           content varchar(2000),
                           user_id integer REFERENCES users(id)
);