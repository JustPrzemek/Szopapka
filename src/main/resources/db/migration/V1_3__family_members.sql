ALTER TABLE shopping DROP COLUMN date;

ALTER TABLE shopping ADD COLUMN quantity INTEGER NOT NULL;

CREATE VIEW family_members AS
SELECT f.family_name, f.image,u.mail, f.family_code
FROM user_in_family uif
    INNER JOIN users u ON uif.id_user = u.id
    INNER JOIN "family" f ON uif.id_family = f.id;
