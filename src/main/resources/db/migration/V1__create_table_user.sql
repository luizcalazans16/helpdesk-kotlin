CREATE TABLE IF NOT EXISTS users(
    id uuid NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(100) NOT NULL
);