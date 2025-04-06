DROP VIEW family_members;

ALTER TABLE shopping
ALTER COLUMN status SET DEFAULT 'NEW';

ALTER TABLE shopping
ADD COLUMN id_user INT,
ADD CONSTRAINT fk_id_user
FOREIGN KEY (id_user) REFERENCES users(id);


CREATE VIEW shopping_view AS
SELECT
    s.id,
    s.id_family,
    s.content,
    s.status,
    s.quantity,
    s.id_user,
    u.mail AS user_name,
    f.family_name
FROM public.shopping s
         INNER JOIN users u ON u.id = s.id_user
         INNER JOIN family f ON f.id = s.id_family;

CREATE OR REPLACE VIEW family_members AS
SELECT
    f.id AS family_id,
    f.family_name,
    f.image,
    u.mail,
    f.family_code
FROM user_in_family uif
         INNER JOIN users u ON uif.id_user = u.id
         INNER JOIN family f ON uif.id_family = f.id;