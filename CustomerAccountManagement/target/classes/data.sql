
INSERT INTO app_role (id, role_name, description) VALUES (1, 'ROLE_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ROLE_ADMIN', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password for user: keypwd
-- non-encrypted password for admin: adminpwd
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (1, 'Keerthi', 'Gadde', '$2y$12$LR3aZ2PLQNxYgKkM5PG4f.C/swW94QryxRDShFrhJ3FSYajDsCy02', 'keerthi.gadde');
INSERT INTO app_user (id, first_name, last_name, password, username) VALUES (2, 'Admin', 'Admin', '$2y$12$t6JbO8Elo/0pcDq76FuzQeHzy3VsXifvsGPxfJwhZB9i7MfGr8agm', 'admin.admin');


INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);

