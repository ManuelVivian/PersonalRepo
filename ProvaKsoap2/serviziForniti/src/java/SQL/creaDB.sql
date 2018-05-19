/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  manuel.vivian
 * Created: 13-mar-2018
 */

PRAGMA foreign_keys = 1;

DROP TABLE IF EXISTS HOBBY;
DROP TABLE IF EXISTS OSPITE;
DROP TABLE IF EXISTS UTENTE;
DROP TABLE IF EXISTS GRUPPO;
DROP TABLE IF EXISTS CHAT;
DROP TABLE IF EXISTS MESSAGGIO;

/* -----------------------------------------------------------------------------
 Creazione tabelle
*/

CREATE TABLE HOBBY (
  idHobby INTEGER PRIMARY KEY, -- chiave primaria
  descrizione VARCHAR(30)
);

CREATE TABLE OSPITE (
  idOspite INTEGER PRIMARY KEY
);

CREATE TABLE UTENTE (
  idUtente INTEGER PRIMARY KEY,
  email VARCHAR(50),
  password VARCHAR(50),
  cognomeNome VARCHAR(50),
  indirizzo VARCHAR(50),
  sesso VARCHAR(1), --M o F
  dataNascita VARCHAR(10), -- data come stringa AAAA-MM-GG
  idFoto IMAGE,
  telefono VARCHAR(20),
  idHobby INTEGER,
  permessi INTEGER NOT NULL, --se 0 utente normale, se 1 amministratore gruppo, se 2 amministratore social
  FOREIGN KEY(idHobby) REFERENCES HOBBY(idHobby)
);
 

CREATE TABLE GRUPPO(
  idGruppo INTEGER PRIMARY KEY, -- chiave primaria
  idUtente INTEGER, 
  FOREIGN KEY(idUtente) REFERENCES UTENTE(idUtente)
);

CREATE TABLE CHAT (
  idChat INTEGER PRIMARY KEY, 
  idGruppo INTEGER, 
  FOREIGN KEY(idGruppo) REFERENCES GRUPPO(idGruppo)
);

CREATE TABLE MESSAGGIO(
  idMessaggio INTEGER PRIMARY KEY,
  contenuto LONGTEXT,
  idUtente INTEGER,
  idChat INTEGER,
  FOREIGN KEY(idUtente)REFERENCES UTENTE(idUtente),
  FOREIGN KEY(idChat)REFERENCES CHAT(idChat)
);

INSERT INTO HOBBY(idHobby,descrizione) VALUES(1,'Calcio');
INSERT INTO HOBBY(idHobby,descrizione) VALUES(2,'Pallavolo');
INSERT INTO HOBBY(idHobby,descrizione) VALUES(3,'Motori');
INSERT INTO HOBBY(idHobby,descrizione) VALUES(4,'Arte');
INSERT INTO HOBBY(idHobby,descrizione) VALUES(5,'Lettura');


INSERT INTO UTENTE(idUtente,email,password,cognomeNome,indirizzo,sesso,dataNascita,idFoto,telefono,idHobby,permessi) VALUES (1,'ghamod995b@mgrover.cf','0000','Rossi Mario','Roma','M','1985-02-02','@abab.jpeg300x300','3458889999',1,0);
INSERT INTO UTENTE(idUtente,email,password,cognomeNome,indirizzo,sesso,dataNascita,idFoto,telefono,idHobby,permessi) VALUES (2,'Uporly@fleckens.hu','1111','LaRosa Giuseppe','Sicilia','M','1996-05-08','@padrino.jpeg300x300','3456786789',5,0);
INSERT INTO UTENTE(idUtente,email,password,cognomeNome,indirizzo,sesso,dataNascita,idFoto,telefono,idHobby,permessi) VALUES (3,'ghamod995b@mgrover.cf','2222','Pina Maria','Venezia','F','1955-12-02','@Liberty.jpeg300x300','3454400341',3,2);