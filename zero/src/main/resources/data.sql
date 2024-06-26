INSERT INTO ZERO_USER (ID, NAME, LOGIN, PASSWORD, EMAIL) VALUES (1, 'Rogério', 'rogerio', '1234','rogerionj@gmail.com');

ALTER SEQUENCE ZERO_USER_ID_SEQ RESTART WITH 2;

INSERT INTO ZERO_PROFILE (ID, DESCRIPTION) VALUES (1, 'Administrador');
INSERT INTO ZERO_PROFILE (ID, DESCRIPTION) VALUES (2, 'Gerente');
INSERT INTO ZERO_PROFILE (ID, DESCRIPTION) VALUES (3, 'Cliente');

ALTER SEQUENCE ZERO_PROFILE_SEQ RESTART WITH 4;

INSERT INTO ZERO_RESOURCE (ID, NAME, KEY) VALUES (1, 'Tela Usuário', 'usuario');
INSERT INTO ZERO_RESOURCE (ID, NAME, KEY) VALUES (2, 'Tela Perfil', 'perfil');
INSERT INTO  ZERO_RESOURCE(ID, NAME, KEY) VALUES (3, 'Tela Recurso', 'recurso');

ALTER SEQUENCE ZERO_RESOURCE_SEQ RESTART WITH 4;