CREATE TABLE family
(
    id SERIAL PRIMARY KEY,
    family_name VARCHAR(255)
);

CREATE TABLE users
(
  id SERIAL PRIMARY KEY,
  mail VARCHAR(255)
);

CREATE TABLE status
(
    id SERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL CHECK (status IN ('DONE', 'DURING', 'NEW'))
);

CREATE TABLE user_in_family
(
    id SERIAL PRIMARY KEY,
    id_user BIGINT NOT NULL,
    id_family BIGINT NOT NULL,

    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (id_family) REFERENCES family (id) ON DELETE CASCADE

);

CREATE TABLE shopping
(
    id SERIAL PRIMARY KEY,
    id_user BIGINT,
    id_family BIGINT NOT NULL,
    date DATE,
    content TEXT,
    id_status BIGINT NOT NULL,

    FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (id_family) REFERENCES family (id) ON DELETE CASCADE,
    FOREIGN KEY (id_status) REFERENCES status (id) ON DELETE CASCADE
);

