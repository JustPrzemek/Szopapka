ALTER TABLE user_in_family
ADD CONSTRAINT unique_user_family
UNIQUE (id_user, id_family);