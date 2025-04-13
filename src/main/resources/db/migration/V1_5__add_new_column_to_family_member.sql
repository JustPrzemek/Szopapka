DROP VIEW family_members;

CREATE OR REPLACE VIEW family_members AS
SELECT
    f.id AS family_id,
    u.id AS user_id,
    f.family_name,
    f.image,
    u.mail,
    f.family_code
FROM user_in_family uif
         INNER JOIN users u ON uif.id_user = u.id
         INNER JOIN family f ON uif.id_family = f.id;