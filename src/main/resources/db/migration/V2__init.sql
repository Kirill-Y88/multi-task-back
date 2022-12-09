INSERT INTO user_roles (title) VALUES
                                   ('ROLE_USER'),
                                   ('ROLE_ADMIN');

INSERT INTO users (login, password, email,role_id) VALUES
('Pavel', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 'pavel@ru' ,1),
('Kirill', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 'kirill@com', 1);

INSERT INTO notes (title, content,user_id) VALUES
('noteOne', 'july talk - Touch \\n NIN', 1),
('noteTwo', 'The black keys', 1),
('Запись-1', 'Мыслей у него не было, лишь пустота', 2);