CREATE TABLE users (
    id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    username CHAR(20) NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    phone_number TEXT NOT NULL
)