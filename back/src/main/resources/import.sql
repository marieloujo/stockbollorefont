



INSERT INTO public.etat (id, created_by, created_date, last_modified_by, last_modified_date, code, libelle) VALUES (1, 'system', '2021-05-24 10:05:05.672', 'system', '2020-08-11 10:05:05.672', 'NEW', 'NEUF');


INSERT INTO public.etat (id, created_by, created_date, last_modified_by, last_modified_date, code, libelle) VALUES (4, 'system', '2021-05-24 10:05:05.672', 'system', '2020-08-11 10:05:05.672', 'HS', 'HORS SERVICE');


INSERT INTO public.etat (id, created_by, created_date, last_modified_by, last_modified_date, code, libelle) VALUES (6, 'system', '2021-05-24 10:05:05.672', 'system', '2020-08-11 10:05:05.672', 'ETAT', 'EN ETAT');


INSERT INTO public.roles (name) values ('ROLE_PERSONNE'), ('ROLE_GESTIONNAIRE'), ('ROLE_DEMANDEUR'), ('ROLE_VALIDATEUR'), ('ROLE_ADMIN'),('ROLE_AUDITEUR');


INSERT INTO public.personne(id,created_by, created_date, last_modified_by, last_modified_date, email, nom, prenom, sexe, password, username) values ('1154','system','2021-05-23 16:14:30.414','system','2021-05-23 16:14:30.414','admin@gmail.com','admin','admin','Masculin','$2a$10$sLLQ8V6BrNZaVmmtsLu5FexLVp6BYwXSWfMsZmeiDc8lmxdG1Ttd6','admin');

INSERT INTO public.user_roles(user_id, role_id) values ('1154', '5');
