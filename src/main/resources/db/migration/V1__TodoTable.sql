CREATE TABLE todo (
        id UUID NOT NULL PRIMARY KEY,
        title VARCHAR(100) NOT NULL,
        description VARCHAR(1000),
        isDone BOOLEAN NOT NULL,
        dateCreated TIMESTAMPTZ
)