SET ECHO ON
SET VERIFY ON
SET FEEDBACK OFF
SET DEFINE ON
CLEAR SCREEN
set serveroutput on

COLUMN date_time NEW_VAL filename noprint;
SELECT to_char(systimestamp,'yyyy-mm-dd_hh24-mi-ssxff') date_time FROM DUAL;
spool "Oracle_Erronka_&filename..log"

-- Password file execution
@passworddefinition.sql

PROMPT Dropping Role ROLE_Oracle_Erronka ...
DROP ROLE ROLE_Oracle_Erronka ;
PROMPT Creating Role ROLE_Oracle_Erronka ...
CREATE ROLE ROLE_Oracle_Erronka ;

-- PROMPT Drop erleak user
-- drop user erleak cascade;
   
PROMPT Create user erleak
CREATE USER erleak IDENTIFIED BY &&erleak_password PASSWORD EXPIRE ACCOUNT LOCK /* DEFAULT TABLESPACE USERS  TEMPORARY TABLESPACE TEMP */;
GRANT CREATE SESSION, RESOURCE, CREATE VIEW, CREATE MATERIALIZED VIEW, CREATE SYNONYM, UNLIMITED TABLESPACE TO erleak;

-- PROMPT Drop Emulation user
-- drop user Emulation cascade;
   
PROMPT Create user Emulation
CREATE USER Emulation IDENTIFIED BY &&Emulation_password PASSWORD EXPIRE ACCOUNT LOCK /* DEFAULT TABLESPACE USERS  TEMPORARY TABLESPACE TEMP */;
GRANT CREATE SESSION, RESOURCE, CREATE VIEW, CREATE MATERIALIZED VIEW, CREATE SYNONYM, UNLIMITED TABLESPACE TO Emulation;

set define on
prompt connecting to Emulation
alter session set current_schema=Emulation;
set define off
set define on
prompt connecting to erleak
alter session set current_schema=erleak;
set define off
-- DROP TABLE asoziazio_parte CASCADE CONSTRAINTS;


PROMPT Creating Table asoziazio_parte ...
CREATE TABLE asoziazio_parte (
  id_asoziazioa NUMBER(10,0) NOT NULL,
  id_sozioa NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY on table asoziazio_parte ... 
ALTER TABLE asoziazio_parte
ADD CONSTRAINT PRIMARY PRIMARY KEY
(
  id_asoziazioa,
  id_sozioa
)
ENABLE
;

GRANT ALL ON asoziazio_parte TO ROLE_Oracle_Erronka;
-- DROP TABLE asoziazioak CASCADE CONSTRAINTS;


PROMPT Creating Table asoziazioak ...
CREATE TABLE asoziazioak (
  asoziazio_izena CHAR(40 CHAR) NOT NULL,
  herrialdea CHAR(25 CHAR) NOT NULL,
  id_asoziazioa NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_15 on table asoziazioak ... 
ALTER TABLE asoziazioak
ADD CONSTRAINT PRIMARY_15 PRIMARY KEY
(
  id_asoziazioa
)
ENABLE
;
PROMPT Creating Unique Constraint asoziazio_izena on table asoziazioak
ALTER TABLE asoziazioak
ADD CONSTRAINT asoziazio_izena UNIQUE (
  asoziazio_izena
)
ENABLE
;

GRANT ALL ON asoziazioak TO ROLE_Oracle_Erronka;
-- DROP TABLE besteak CASCADE CONSTRAINTS;


PROMPT Creating Table besteak ...
CREATE TABLE besteak (
  id_produktua NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_2 on table besteak ... 
ALTER TABLE besteak
ADD CONSTRAINT PRIMARY_2 PRIMARY KEY
(
  id_produktua
)
ENABLE
;

GRANT ALL ON besteak TO ROLE_Oracle_Erronka;
-- DROP TABLE bulegoak CASCADE CONSTRAINTS;


PROMPT Creating Table bulegoak ...
CREATE TABLE bulegoak (
  herria CHAR(25 CHAR) NOT NULL,
  id_asoziazioa NUMBER(10,0),
  id_bulegoa NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_3 on table bulegoak ... 
ALTER TABLE bulegoak
ADD CONSTRAINT PRIMARY_3 PRIMARY KEY
(
  id_bulegoa
)
ENABLE
;

GRANT ALL ON bulegoak TO ROLE_Oracle_Erronka;
-- DROP TABLE etsailak CASCADE CONSTRAINTS;


PROMPT Creating Table etsailak ...
CREATE TABLE etsailak (
  deskribapena CHAR(100 CHAR),
  hildako_kan NUMBER(10,0),
  id_etsaia NUMBER(10,0) NOT NULL,
  izena CHAR(25 CHAR) NOT NULL,
  kanpotarra NUMBER(3,0)
);


ALTER TABLE etsailak MODIFY (hildako_kan DEFAULT '0');
ALTER TABLE etsailak MODIFY (kanpotarra DEFAULT '0');
PROMPT Creating Primary Key Constraint PRIMARY_4 on table etsailak ... 
ALTER TABLE etsailak
ADD CONSTRAINT PRIMARY_4 PRIMARY KEY
(
  id_etsaia
)
ENABLE
;

GRANT ALL ON etsailak TO ROLE_Oracle_Erronka;
-- DROP TABLE eztia CASCADE CONSTRAINTS;


PROMPT Creating Table eztia ...
CREATE TABLE eztia (
  eztia_mota CHAR(50 CHAR) NOT NULL,
  id_lorea NUMBER(10,0),
  id_produktua NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_5 on table eztia ... 
ALTER TABLE eztia
ADD CONSTRAINT PRIMARY_5 PRIMARY KEY
(
  id_produktua
)
ENABLE
;

GRANT ALL ON eztia TO ROLE_Oracle_Erronka;
-- DROP TABLE fabrikatzailea CASCADE CONSTRAINTS;


PROMPT Creating Table fabrikatzailea ...
CREATE TABLE fabrikatzailea (
  helbidea CHAR(100 CHAR),
  hiria CHAR(70 CHAR) NOT NULL,
  id_fabrikatzailea NUMBER(10,0) NOT NULL,
  izena CHAR(50 CHAR) NOT NULL,
  korreoa CHAR(50 CHAR),
  telefonoa NUMBER(24,0)
);


PROMPT Creating Primary Key Constraint PRIMARY_6 on table fabrikatzailea ... 
ALTER TABLE fabrikatzailea
ADD CONSTRAINT PRIMARY_6 PRIMARY KEY
(
  id_fabrikatzailea
)
ENABLE
;

GRANT ALL ON fabrikatzailea TO ROLE_Oracle_Erronka;
-- DROP TABLE hymenoptera_europan CASCADE CONSTRAINTS;


PROMPT Creating Table hymenoptera_europan ...
CREATE TABLE hymenoptera_europan (
  europearrak_edo_ez NUMBER(3,0),
  id_hymenoptera NUMBER(10,0) NOT NULL,
  id_produktua NUMBER(10,0),
  izena CHAR(25 CHAR) NOT NULL,
  kantitatea NUMBER(10,0),
  kokapena CHAR(50 CHAR)
);


ALTER TABLE hymenoptera_europan MODIFY (europearrak_edo_ez DEFAULT '1');
ALTER TABLE hymenoptera_europan MODIFY (kantitatea DEFAULT '0');
PROMPT Creating Primary Key Constraint PRIMARY_7 on table hymenoptera_europan ... 
ALTER TABLE hymenoptera_europan
ADD CONSTRAINT PRIMARY_7 PRIMARY KEY
(
  id_hymenoptera
)
ENABLE
;

GRANT ALL ON hymenoptera_europan TO ROLE_Oracle_Erronka;
-- DROP TABLE loreak CASCADE CONSTRAINTS;


PROMPT Creating Table loreak ...
CREATE TABLE loreak (
  id_erlea NUMBER(10,0),
  id_lorea NUMBER(10,0) NOT NULL,
  izena CHAR(25 CHAR) NOT NULL,
  kantitatea NUMBER(10,0),
  kokapena CHAR(50 CHAR)
);


PROMPT Creating Primary Key Constraint PRIMARY_8 on table loreak ... 
ALTER TABLE loreak
ADD CONSTRAINT PRIMARY_8 PRIMARY KEY
(
  id_lorea
)
ENABLE
;

GRANT ALL ON loreak TO ROLE_Oracle_Erronka;
-- DROP TABLE materiala CASCADE CONSTRAINTS;


PROMPT Creating Table materiala ...
CREATE TABLE materiala (
  deskribapena CHAR(200 CHAR),
  id_fabrikatzailea NUMBER(10,0) NOT NULL,
  id_materiala NUMBER(10,0) NOT NULL,
  kantitatea NUMBER(10,0),
  materiala_izena CHAR(50 CHAR) NOT NULL,
  prezioa NUMBER(10,0)
);


PROMPT Creating Primary Key Constraint PRIMARY_9 on table materiala ... 
ALTER TABLE materiala
ADD CONSTRAINT PRIMARY_9 PRIMARY KEY
(
  id_materiala,
  id_fabrikatzailea
)
ENABLE
;

GRANT ALL ON materiala TO ROLE_Oracle_Erronka;
-- DROP TABLE produktuak CASCADE CONSTRAINTS;


PROMPT Creating Table produktuak ...
CREATE TABLE produktuak (
  deskribapena CHAR(200 CHAR),
  id_produktua NUMBER(10,0) NOT NULL,
  kantitatea NUMBER(10,0),
  prezioa NUMBER(10,0),
  produktu_mota CHAR(50 CHAR) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_10 on table produktuak ... 
ALTER TABLE produktuak
ADD CONSTRAINT PRIMARY_10 PRIMARY KEY
(
  id_produktua
)
ENABLE
;

GRANT ALL ON produktuak TO ROLE_Oracle_Erronka;
-- DROP TABLE sozioak CASCADE CONSTRAINTS;


PROMPT Creating Table sozioak ...
CREATE TABLE sozioak (
  email CHAR(50 CHAR),
  erle_kantitatea NUMBER(10,0),
  id_sozioa NUMBER(10,0) NOT NULL,
  id_zuzendaria NUMBER(10,0),
  jaiote_eguna DATE,
  kolmena_kantitatea NUMBER(10,0),
  NAN CHAR(25 CHAR),
  sozio_abizena CHAR(25 CHAR) NOT NULL,
  sozio_izena CHAR(25 CHAR) NOT NULL,
  telefonoa NUMBER(24,0)
);


ALTER TABLE sozioak MODIFY (erle_kantitatea DEFAULT '0');
ALTER TABLE sozioak MODIFY (kolmena_kantitatea DEFAULT '0');
PROMPT Creating Primary Key Constraint PRIMARY_11 on table sozioak ... 
ALTER TABLE sozioak
ADD CONSTRAINT PRIMARY_11 PRIMARY KEY
(
  id_sozioa
)
ENABLE
;
PROMPT Creating Unique Constraint NAN on table sozioak
ALTER TABLE sozioak
ADD CONSTRAINT NAN UNIQUE (
  NAN
)
ENABLE
;

GRANT ALL ON sozioak TO ROLE_Oracle_Erronka;
-- DROP TABLE ze_ezti CASCADE CONSTRAINTS;


PROMPT Creating Table ze_ezti ...
CREATE TABLE ze_ezti (
  id_produktua NUMBER(10,0) NOT NULL,
  id_sozioa NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_12 on table ze_ezti ... 
ALTER TABLE ze_ezti
ADD CONSTRAINT PRIMARY_12 PRIMARY KEY
(
  id_sozioa,
  id_produktua
)
ENABLE
;

GRANT ALL ON ze_ezti TO ROLE_Oracle_Erronka;
-- DROP TABLE zein_etsai CASCADE CONSTRAINTS;


PROMPT Creating Table zein_etsai ...
CREATE TABLE zein_etsai (
  id_etsaia NUMBER(10,0) NOT NULL,
  id_hymenoptera NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_13 on table zein_etsai ... 
ALTER TABLE zein_etsai
ADD CONSTRAINT PRIMARY_13 PRIMARY KEY
(
  id_etsaia,
  id_hymenoptera
)
ENABLE
;

GRANT ALL ON zein_etsai TO ROLE_Oracle_Erronka;
-- DROP TABLE zein_produktu CASCADE CONSTRAINTS;


PROMPT Creating Table zein_produktu ...
CREATE TABLE zein_produktu (
  id_asoziazioa NUMBER(10,0) NOT NULL,
  id_produktua NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_14 on table zein_produktu ... 
ALTER TABLE zein_produktu
ADD CONSTRAINT PRIMARY_14 PRIMARY KEY
(
  id_produktua,
  id_asoziazioa
)
ENABLE
;

GRANT ALL ON zein_produktu TO ROLE_Oracle_Erronka;
-- DROP TABLE zenbat_material CASCADE CONSTRAINTS;


PROMPT Creating Table zenbat_material ...
CREATE TABLE zenbat_material (
  id_asoziazioa NUMBER(10,0) NOT NULL,
  id_materiala NUMBER(10,0) NOT NULL
);


PROMPT Creating Primary Key Constraint PRIMARY_1 on table zenbat_material ... 
ALTER TABLE zenbat_material
ADD CONSTRAINT PRIMARY_1 PRIMARY KEY
(
  id_materiala,
  id_asoziazioa
)
ENABLE
;

GRANT ALL ON zenbat_material TO ROLE_Oracle_Erronka;
PROMPT Creating Index id_sozioa on asoziazio_parte ...
CREATE INDEX id_sozioa ON asoziazio_parte
(
  id_sozioa
) 
;
PROMPT Creating Index id_asoziazioa_1 on bulegoak ...
CREATE INDEX id_asoziazioa_1 ON bulegoak
(
  id_asoziazioa
) 
;
PROMPT Creating Index id_lorea on eztia ...
CREATE INDEX id_lorea ON eztia
(
  id_lorea
) 
;
PROMPT Creating Index id_produktua_1 on hymenoptera_europan ...
CREATE INDEX id_produktua_1 ON hymenoptera_europan
(
  id_produktua
) 
;
PROMPT Creating Index id_fabrikatzailea on materiala ...
CREATE INDEX id_fabrikatzailea ON materiala
(
  id_fabrikatzailea
) 
;
PROMPT Creating Index id_zuzendaria on sozioak ...
CREATE INDEX id_zuzendaria ON sozioak
(
  id_zuzendaria
) 
;
PROMPT Creating Index id_produktua on ze_ezti ...
CREATE INDEX id_produktua ON ze_ezti
(
  id_produktua
) 
;
PROMPT Creating Index id_hymenoptera on zein_etsai ...
CREATE INDEX id_hymenoptera ON zein_etsai
(
  id_hymenoptera
) 
;
PROMPT Creating Index id_asoziazioa on zein_produktu ...
CREATE INDEX id_asoziazioa ON zein_produktu
(
  id_asoziazioa
) 
;
PROMPT Creating Index id_asoziazioa_2 on zenbat_material ...
CREATE INDEX id_asoziazioa_2 ON zenbat_material
(
  id_asoziazioa
) 
;
set define on
prompt connecting to erleak
alter session set current_schema=erleak;
set define off
PROMPT Creating Foreign Key Constraint asoziazio_parte_ibfk_2 on table sozioak...
ALTER TABLE asoziazio_parte
ADD CONSTRAINT asoziazio_parte_ibfk_2 FOREIGN KEY
(
  id_sozioa
)
REFERENCES sozioak
(
  id_sozioa
)
ENABLE
;

PROMPT Creating Foreign Key Constraint zenbat_material_ibfk_1 on table materiala...
ALTER TABLE zenbat_material
ADD CONSTRAINT zenbat_material_ibfk_1 FOREIGN KEY
(
  id_materiala
)
REFERENCES materiala
(
  id_materiala
)
ENABLE
;

PROMPT Creating Foreign Key Constraint besteak_ibfk_1 on table produktuak...
ALTER TABLE besteak
ADD CONSTRAINT besteak_ibfk_1 FOREIGN KEY
(
  id_produktua
)
REFERENCES produktuak
(
  id_produktua
)
ENABLE
;

PROMPT Creating Foreign Key Constraint eztia_ibfk_1 on table produktuak...
ALTER TABLE eztia
ADD CONSTRAINT eztia_ibfk_1 FOREIGN KEY
(
  id_produktua
)
REFERENCES produktuak
(
  id_produktua
)
ENABLE
;

PROMPT Creating Foreign Key Constraint eztia_ibfk_2 on table loreak...
ALTER TABLE eztia
ADD CONSTRAINT eztia_ibfk_2 FOREIGN KEY
(
  id_lorea
)
REFERENCES loreak
(
  id_lorea
)
ENABLE
;

PROMPT Creating Foreign Key Constraint hymenoptera_europan_ibfk_1 on table besteak...
ALTER TABLE hymenoptera_europan
ADD CONSTRAINT hymenoptera_europan_ibfk_1 FOREIGN KEY
(
  id_produktua
)
REFERENCES besteak
(
  id_produktua
)
ENABLE
;

PROMPT Creating Foreign Key Constraint hymenoptera_europan_ibfk_2 on table eztia...
ALTER TABLE hymenoptera_europan
ADD CONSTRAINT hymenoptera_europan_ibfk_2 FOREIGN KEY
(
  id_produktua
)
REFERENCES eztia
(
  id_produktua
)
ENABLE
;

PROMPT Creating Foreign Key Constraint materiala_ibfk_1 on table fabrikatzailea...
ALTER TABLE materiala
ADD CONSTRAINT materiala_ibfk_1 FOREIGN KEY
(
  id_fabrikatzailea
)
REFERENCES fabrikatzailea
(
  id_fabrikatzailea
)
ENABLE
;

PROMPT Creating Foreign Key Constraint sozioak_ibfk_1 on table sozioak...
ALTER TABLE sozioak
ADD CONSTRAINT sozioak_ibfk_1 FOREIGN KEY
(
  id_zuzendaria
)
REFERENCES sozioak
(
  id_sozioa
)
ENABLE
;

PROMPT Creating Foreign Key Constraint ze_ezti_ibfk_1 on table sozioak...
ALTER TABLE ze_ezti
ADD CONSTRAINT ze_ezti_ibfk_1 FOREIGN KEY
(
  id_sozioa
)
REFERENCES sozioak
(
  id_sozioa
)
ENABLE
;

PROMPT Creating Foreign Key Constraint ze_ezti_ibfk_2 on table produktuak...
ALTER TABLE ze_ezti
ADD CONSTRAINT ze_ezti_ibfk_2 FOREIGN KEY
(
  id_produktua
)
REFERENCES produktuak
(
  id_produktua
)
ENABLE
;

PROMPT Creating Foreign Key Constraint zein_etsai_ibfk_1 on table etsailak...
ALTER TABLE zein_etsai
ADD CONSTRAINT zein_etsai_ibfk_1 FOREIGN KEY
(
  id_etsaia
)
REFERENCES etsailak
(
  id_etsaia
)
ENABLE
;

PROMPT Creating Foreign Key Constraint zein_etsai_ibfk_2 on table hymenoptera_europan...
ALTER TABLE zein_etsai
ADD CONSTRAINT zein_etsai_ibfk_2 FOREIGN KEY
(
  id_hymenoptera
)
REFERENCES hymenoptera_europan
(
  id_hymenoptera
)
ENABLE
;

PROMPT Creating Foreign Key Constraint zein_produktu_ibfk_1 on table produktuak...
ALTER TABLE zein_produktu
ADD CONSTRAINT zein_produktu_ibfk_1 FOREIGN KEY
(
  id_produktua
)
REFERENCES produktuak
(
  id_produktua
)
ENABLE
;

PROMPT Creating Foreign Key Constraint zein_produktu_ibfk_2 on table asoziazioak...
ALTER TABLE zein_produktu
ADD CONSTRAINT zein_produktu_ibfk_2 FOREIGN KEY
(
  id_asoziazioa
)
REFERENCES asoziazioak
(
  id_asoziazioa
)
ENABLE
;

spool off;

COMMIT;

