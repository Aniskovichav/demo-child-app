CREATE TABLE child
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    age  INT NULL,
    CONSTRAINT pk_child PRIMARY KEY (id)
);

INSERT INTO child (name, age) VALUES ('Alex', 20);
INSERT INTO child (name, age) VALUES ('John', 21);
