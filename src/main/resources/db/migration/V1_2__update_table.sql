ALTER TABLE shopping DROP COLUMN id_user;

ALTER TABLE family ADD "image" VARCHAR(255);

ALTER TABLE shopping DROP COLUMN id_status;

DROP TABLE status;

ALTER TABLE shopping ADD COLUMN status VARCHAR(255) NOT NULL CHECK (status IN ('DONE', 'DURING', 'NEW'));
