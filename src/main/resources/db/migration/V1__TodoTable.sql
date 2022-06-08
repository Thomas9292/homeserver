CREATE TABLE todo (
    id UUID NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    is_done BOOLEAN NOT NULL,
    date_created TIMESTAMP
)