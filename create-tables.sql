BEGIN;

CREATE TABLE authors (
    id serial not null primary key,
    name text not null,
    contact varchar(50) default null,
    created_at timestamp not null default now(),
    updated_at timestamp null default now()
);

CREATE TABLE genres (
    id serial not null primary key,
    type text not null
);

CREATE TABLE books (
    id serial not null primary key,
    author_id integer not null references authors (id),
    genre_id integer not null references genres (id),
    title text not null,
    isbn varchar(255) default null,
    publication_year varchar(50) default null,
    created_at timestamp not null default now(),
    updated_at timestamp null default now()
);

COMMIT;