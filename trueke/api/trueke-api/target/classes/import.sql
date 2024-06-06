--ASSOCIATIONS
insert into ASSOCIATIONS (ID, ADDRESS, CIF, MISSION, NAME) values ('3f656426-4d1b-4c76-81e5-1136a0734b03', 'c/Amapolas, 3', '337164184-8', 'Ayudar a los mas necesitados', 'Carmelitas ONG');
insert into ASSOCIATIONS (ID, ADDRESS, CIF, MISSION, NAME) values ('2db41aae-cdcd-47a1-9ea0-162ccb33a444', 'c/General Prieto, 65', '575176671-7', 'Difundimos la ayuda de Dios', 'Fundacion Salesiana');
insert into ASSOCIATIONS (ID, ADDRESS, CIF, MISSION, NAME) values ('3db7aa06-5db8-4d18-9dd5-5bd0446c3bf8', 'Avd. Rey Alfonso X, 12, 4ºJ', '175877671-2', 'De las drogas se sale', 'Proyecto Hombre');

--STAFF WORKERS
insert into USERS (ID, NAME, SURNAME, USERNAME, EMAIL, ROLES, PASSWORD, LAST_PASSWORD_CHANGE_AT, CREATED_AT, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED) values ('8349cbb8-23c1-46e4-a58d-090509c7280e', 'Pau', 'Cabrera Bayarri', 'pau95', 'pau@gmail.com', 'STAFF,WORKER', '{bcrypt}$2a$12$0.PGpFxuXIfaypN.k5e5/eSW8UfUtDMbteJDb4lUWlOj8dbnBHJaW', DATEADD(MONTH,-2,CURRENT_DATE), DATEADD(MONTH,-2,CURRENT_DATE), TRUE, TRUE, TRUE, TRUE);
    insert into WORKERS (ASSOCIATION_ID, ID, CAREER, DNI, PHONE_NUMBER) values ('3db7aa06-5db8-4d18-9dd5-5bd0446c3bf8', (select ID from USERS where username = 'pau95'), 'Repartidor del Dominos durante mas de 10 años', '05722395H', '+34 652 140 885');

--COLLABORATOR
insert into USERS (ID, NAME, SURNAME, USERNAME, EMAIL, ROLES, PASSWORD, LAST_PASSWORD_CHANGE_AT, CREATED_AT, AVATAR_IMG, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED) values ('ffb1177b-6dfa-44eb-804f-86d2e83c6c34', 'Jeronimo Manuel', 'Perez Gonzalez', 'jero93', 'jero@gmail.com', 'COLLABORATOR', '{bcrypt}$2a$12$kjX.ZItbIOwIXWRmEJ/4meL.fFGOKX8pq1NwUuaZLgR8//kZFlIV6', DATEADD(MONTH,-1,CURRENT_DATE), DATEADD(MONTH,-1,CURRENT_DATE), 'yop.jpg', TRUE, TRUE, TRUE, TRUE);
    insert into COLLABORATORS (ID, AGE, MOTIVATION, SKILLS) values ((select ID from USERS where username = 'jero93'), 30, 'Me gusta ayudar a los mas necesitados en sus momentos malos', 'Soy bueno reparando cosas, tambien se programar, soy socorrista y profesor de tenis');

--EVENTS
insert into EVENTS (ID, TITLE, HEADLINE, DESCRIPTION, LOCATION, ASSOCIATION_ID, START_DATE, END_DATE) values ('a2bc6f21-765a-44fa-bb00-6d60e355690e', 'Taller de costura', 'La mejor oportunidad de aprender un oficio', 'Durante el evento tendremos la oportunidad de aprender a coser de la mano de los mas hábiles costureros/as de Almería', 'c/Limonero, 13, 1ºA', '3db7aa06-5db8-4d18-9dd5-5bd0446c3bf8', '2024-07-01 18:00:00', '2024-07-01 20:00:00');