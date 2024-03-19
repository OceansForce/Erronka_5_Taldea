-- Datu basea sortu
create database erleak;

-- Datu basea erabili
use erleak;

-- Taulak sortu eta PKak definitu

create table fabrikatzailea(
	id_fabrikatzailea int,
	izena char(50) not null,
	hiria char(70) not null,
	helbidea char(100),
	telefonoa bigint,
	korreoa char(50),
	primary key(id_fabrikatzailea)
);

create table materiala(
	id_materiala int,
	id_fabrikatzailea int,
	materiala_izena char(50) not null,
	prezioa int,
	deskribapena char(200),
	kantitatea int,
	primary key(id_materiala, id_fabrikatzailea)
);

create table bulegoak(
	id_bulegoa int,
	id_asoziazioa int,
	herria char (25) not null,
	primary key(id_bulegoa)
);

create table zenbat_material(
	id_materiala int,
	id_asoziazioa int,
	primary key(id_materiala, id_asoziazioa)
);

create table asoziazioak(
	id_asoziazioa int,
	asoziazio_izena char (40) not null unique,
	herrialdea char (25) not null,
	primary key(id_asoziazioa)
);
create table asoziazio_parte(
	id_asoziazioa int,
	id_sozioa int,
	primary key(id_asoziazioa, id_sozioa)
);

create table zein_produktu(
	id_produktua int,
	id_asoziazioa int,
	primary key(id_produktua, id_asoziazioa)
);

create table sozioak(
	id_sozioa int,
	id_zuzendaria int,
	erle_kantitatea int default 0,
	kolmena_kantitatea int default 0,
	sozio_izena char(25) not null,
	sozio_abizena char(25) not null,
	NAN char(25) unique,
	telefonoa bigint,
	jaiote_eguna date,
	email char(50),
	CHECK (LENGTH(NAN) BETWEEN 9 AND 10),
	primary key(id_sozioa)
);

create table ze_ezti(
	id_sozioa int,
	id_produktua int,
	primary key(id_sozioa, id_produktua)
);

create table produktuak(
	id_produktua int,
	kantitatea int,
	produktu_mota char (50) not null,
	prezioa int,
	deskribapena char(200),
	primary key(id_produktua)
);

create table besteak(
	id_produktua int,
	primary key(id_produktua)
);

create table eztia(
	id_produktua int,
	id_lorea int,
	eztia_mota char (50) not null,
	primary key(id_produktua)
);

create table loreak(
	id_lorea int,
	id_erlea int,
	izena char(25) not null,
	kokapena char (50),
	kantitatea int,
	primary key(id_lorea)
);

create table hymenoptera_europan(
	id_hymenoptera int,
	id_produktua int,
	kantitatea int default 0,
	europearrak_edo_ez boolean default true,
	kokapena char (50),
	izena char(25) not null,
	primary key(id_hymenoptera)
);

create table zein_etsai(
	id_etsaia int,
	id_hymenoptera int,
	primary key(id_etsaia, id_hymenoptera)
);

create table etsailak(
	id_etsaia int,
	izena char(25) not null,
	kanpotarra boolean default False,
	deskribapena char(100),
	hildako_kan int default 0,
	primary key(id_etsaia)
);

set foreign_key_checks=0;

-- Datuak txertatu
-- Asoziazioa
insert into Asoziazioak (id_asoziazioa, Asoziazio_izena, herrialdea) values
(1, 'Oberbrunner, Beier and Roob', 'Russia'),
 (2, 'Kilback, Donnelly and Stiedemann', 'Zermatt'),
 (3, 'Haag and Sons', 'Sintra'),
 (4, 'Kuhn and Sons', 'Rotorua'),
(5, 'Zboncak-Schmitt', 'Novi Sad'),
(6, 'Ernser-Raynor', 'Nafplio'),
(7, 'Kemmer-Shanahan', 'Bath'),
(8, 'Lynch-Bins', 'Mostar'),
(9, 'Denesik-Kuhn', 'Kyoto'),
(10, 'Legros and Sons', 'Lviv');

-- bulegoak
insert into Bulegoak (id_bulegoa, herria, id_asoziazioa) values
(1, 'Switzerland', 2),
(2, 'Ukraine', 10),
(3, 'United Kingdom', 7),
(4, 'Sweden', 1),
(5, 'Japan', 9),
(6, 'Portugal', 3),
(7, 'Greece', 6),
(8, 'Serbia', 5),
(9, 'New Zelanda', 4),
(10, 'Bosnia and Herzegovina', 8);

-- etsaiak
INSERT INTO Etsailak (id_etsaia, izena, kanpotarra, deskribapena, hildako_kan)
VALUES
(1, 'Vespa velutina', TRUE, 'Europako asian ontzi arrunta. Europa eratorria.', 95),
(2, 'Apis mellifera scutellata', FALSE, 'Afrikako mendian bizi den belar arrunta. Europa kanpotik.', 78),
(3, 'Vespula germanica', TRUE, 'Europako alemaniar ontzi arrunta. Europako hegoaldetik.', 359),
(4, 'Bombus terrestris', FALSE, 'Lurrean bizi den europar belar arrunta. Europa kanpotik.', 416),
(5, 'Vespa crabro', TRUE, 'Europako ontzi arrunta. Europako iparaldetik.', 267),
(6, 'Apis cerana', FALSE, 'Asiako mendian bizi den belar arrunta. Europa kanpotik.', 503),
(7, 'Polistes dominula', TRUE, 'Europako ontzi arrunta. Europako iparaldetik.', 42),
(8, 'Apis dorsata', FALSE, 'Asiako mendian bizi den belar arrunta. Europa kanpotik.', 138),
(9, 'Xylocopa violacea', TRUE, 'Europako ontzi arrunta. Europako hegoaldetik.', 513),
(10, 'Vespa mandarinia', FALSE, 'Asiako ontzi arrunta. Europa kanpotik.', 885);

-- eztiak
insert into Eztia (eztia_mota, id_produktua, id_lorea) values
('manuka', 1, 1),
('akaziko', 3, 3),
('eukalipto', 7, 9),
('erromero', 2, 8),
('lavanda', 9, 4);


-- fabrikatzailea
INSERT INTO Fabrikatzailea (id_fabrikatzailea, izena, hiria, helbidea, telefonoa, korreoa) VALUES
(1, 'Wintheiser Inc', 'Switzerland', '1437 Valdia, District du Jura-Nord vaudois', 278408650849, 'info@wintheiser.com'),
(2, 'Donnelly and Sons', 'Czech Republic', '364 01, Northwest, Karlovarský kraj, okres Karlovy Vary, Toužim', 628498979028, 'contact@donnellyandsons.cz'),
(3, 'Cassin, Reilly and Bayer', 'Lithuania', 'Šiauliai, Pašvitinio seniūnija, Janelioniai', 3705641410559, 'info@cassinreillybayer.lt'),
(4, 'Brekke-Blanda', 'Ireland', 'Tipperary, Street 45626', 2163477598132, 'sales@brekkeblanda.ie'),
(5, 'Robel-Fritsch', 'Belarus', 'Street 61, Klichaw District', 2639397582676, 'support@robelfritsch.by'),
(6, 'Moore-Langosh', 'Serbia', '523 Wiegand Drives', 3811384615528, 'hello@moorelangosh.rs'),
(7, 'Little-Schmidt', 'Iceland', '9923 Stiedemann Stravenue', 869589705248, 'contact@littleschmidt.is'),
(8, 'Wyman Inc', 'Finland', 'Street84, Kakkurintie', 666374555379, 'info@wymaninc.fi'),
(9, 'Koss, Waelchi and Bode', 'France', 'Street 65, Aisne, Vervins', 334991747492, 'info@kossbode.fr'),
(10, 'Herzog, Purdy and Sporer', 'Italy', '10828 Hegmann Mountain', 635201760315, 'hello@herzogsporer.it');



-- hymenoptera
INSERT INTO Hymenoptera_Europan (id_hymenoptera, kantitatea, europearrak_edo_ez, kokapena, izena, id_produktua)
VALUES
(1, 308, TRUE, 'Spain', 'Vespula germanica', 7),
(2, 975, FALSE, 'China', 'Apis cerana', 2),
(3, 340, FALSE, 'Indonesia', 'Vespula flavopilosa', 5),
(4, 974, TRUE, 'Germany', 'Apis mellifera', 9),
(5, 285, TRUE, 'England', 'Formica rufa', 3),
(6, 69, FALSE, 'Indonesia', 'Vespula affinis', 8),
(7, 108, TRUE, 'Poland', 'Vespula vulgaris', 10),
(8, 732, TRUE, 'Macedonia', 'Lasius niger', 4),
(9, 119, TRUE, 'turkey', 'Apis mellifera syriaca', 1),
(10, 902, TRUE, 'Greece', 'Lasius flavus', 6);

-- loreak
insert into Loreak (id_lorea, izena, kokapena, kantitatea, id_erlea) values (1, 'daisy', 'Northern', 284, 1),
(2, 'Chrysanthemum', 'Southern', 535, 2),
(3, 'rose', 'Eastern', 501, 3),
(4, 'sunflower', 'Northern', 188, 4),
(5, 'Amaranth', 'Northern', 627, 5),
(6, 'lily', 'Western', 15, 6),
(7, 'Amorphophallus', 'Northern', 21, 7),
(8, 'Wolffia', 'Southern', 645, 8),
(9, 'Pasionaria', 'Southern', 950, 9),
(10, 'edelweiss', 'Central', 301, 10);


-- materiala
INSERT INTO Materiala (id_materiala, id_fabrikatzailea, materiala_izena, prezioa, deskribapena, kantitatea)
VALUES
(1, 1, 'goma elatua', 233.32, 'Polen kasetak gordetzeko eta banatzeko erabiliko den materiala.', 562),
(2, 1, 'inoxidablezko metala', 935.65, 'Ekoizleak lurrina eta euria erabiliz garatutako polen saskiak gordeko dituen materiala.', 969),
(3, 1, 'galbanizatutako metala', 490.78, 'Polen kasetak gordetzeko eta banatzeko erabiliko den materiala.', 10),
(4, 4, 'larru artifiziala', 121.89, 'Harrizko egitura duen polen kasetak ekoizteko materiala.', 578),
(5, 4, 'egur moldatua', 180.82, 'Ekoizleak egurrez egindako polen kasetak gordetzeko eta banatzeko erabiliko du.', 996),
(12, 6, 'goma elastikoa', 960.29, 'Ekoizleak plastiko elastikoaz egindako polen kasetak gordeko dituen materiala.', 512),
(7, 4, 'larru artifiziala', 465.38, 'Polen kasetak gordetzeko eta banatzeko erabiliko den materiala.', 636),
(8, 8, 'galbanizatutako metala', 233.75, 'Ekoizleak lurrina eta euria erabiliz garatutako polen saskiak gordeko dituen materiala.', 607),
(9, 7, 'inoxidablezko metala', 70.72, 'Ekoizleak lurrina eta euria erabiliz garatutako polen saskiak gordeko dituen materiala.', 618),
(10, 6, 'poliester erreplikatua', 307.35, 'Polen kasetak gordetzeko eta banatzeko erabiliko den materiala.', 909);


-- produktuak
INSERT INTO Produktuak (id_produktua, kantitatea, produktu_mota, prezioa, deskribapena)
VALUES
(1, 818018, 'eztia', 29.55, 'Eztiaren egitura eta jatorriari buruzko ikerketak egiteko erabiliko den produktua'),
(2, 842124, 'eztia', 897.82, 'Erleek sortutako berezko proteina'),
(3, 916240, 'eztia', 108.10, 'Erleen larben garapena sustatzeko erabiliko den produktua'),
(4, 980029, 'apitoksin', 126.36, 'Erleen kontrako defentsa sisteman erabiliko den toxina'),
(5, 290047, 'meliocó', 22.15, 'Erleen jostorratza'),
(6, 415391, 'propolis', 701.28, 'Erleen poizona'),
(7, 678261, 'eztia', 89.49, 'Erleek sortzen dutn janaria'),
(8, 471218, 'trombuzya', 503.33, 'Erleen larben garapena sustatzeko erabiliko den produktua'),
(9, 847826, 'eztia', 919.9, 'Erlek sortutako janaria'),
(10, 666563, 'apitoksin', 982.90, 'Erleen kontrako defentsa sisteman erabiliko den toksina');

-- sozioak
INSERT INTO sozioak (id_sozioa, erle_kantitatea, kolmena_kantitatea, sozio_izena, sozio_abizena, NAN, Email, telefonoa, jaiote_eguna, id_zuzendaria) VALUES
(1, 628988, 955, 'Veriee', 'Janodet', '76390986Y', 'veriee.janodet@gmail.com', 17147601757, '1986-09-11', 4),
(2, 508595, 590, 'Erna', 'St. Aubyn', '36254604Q', 'erna.st.aubyn@hotmail.com', 556105125272, '1991-10-31', 7),
(3, 540617, 876, 'Lion', 'Grimes', '85844584D', 'lion.grimes@yahoo.com', 472458625861, '1984-03-17', 3),
(4, 772010, 592, 'Trace', 'McCloud', '67655397P', 'trace.mccloud@protonmail.com', 861293417120, '2005-04-04', 4),
(5, 362761, 130, 'Ludwig', 'Lanphere', '16880065H', 'ludwig.lanphere@icloud.com', 816729879443, '1995-03-22', 5),
(6, 295054, 392, 'Judd', 'Beneteau', '60338544E', 'judd.beneteau@gmail.com', 79309943838, '1991-12-31', 3),
(7, 316385, 464, 'Annamarie', 'Farrer', '12619117T', 'annamarie.farrer@hotmail.com', 335888714961, '1992-07-27', 7),
(8, 592441, 187, 'Selene', 'Kroger', '58339691R', 'selene.kroger@yahoo.com', 868238294154, '1988-01-28', 8),
(9, 185569, 695, 'Justinn', 'Covell', '99671162P', 'justinn.covell@protonmail.com', 2655361559519, '1984-07-29', 9),
(10, 66724, 503, 'Ali', 'Aicken', '62259722J', 'ali.aicken@gmail.com', 344017459679, '1989-08-21', 4);


-- NM asoziazio sozio
insert into asoziazio_parte (id_asoziazioa, id_sozioa) values
(1, 5),
(3, 3),
(3, 8),
(1, 7),
(5, 9),
(2, 2),
(8, 6),
(5, 4),
(7, 10),
(9, 1);

-- nm zenbat_material
insert into zenbat_material (id_materiala, id_asoziazioa) values
(1, 3),
(2, 7),
(3, 5),
(4, 9),
(5, 2),
(6, 8);

-- NM ze_ezti
INSERT INTO ze_ezti (id_sozioa, id_produktua) VALUES
(3, 6),
(5, 1),
(7, 9),
(2, 4),
(8, 10),
(4, 3),
(1, 7),
(9, 2),
(6, 8),
(10, 5);

-- NM zein_etsai
INSERT INTO zein_etsai (id_etsaia, id_hymenoptera) VALUES
(3, 6),
(5, 1),
(7, 9),
(2, 4),
(8, 10),
(4, 3),
(1, 7),
(9, 2),
(6, 8),
(10, 5);

-- besteak
INSERT INTO besteak () VALUES
(7),
(3),
(9),
(2),
(6),
(8),
(5),
(1),
(4),
(10);

-- Foreign key-ak sortzen

-- Materiala tabla
alter table materiala add foreign key (id_fabrikatzailea) references fabrikatzailea(id_fabrikatzailea)
on update cascade on delete restrict;

-- Bulego tabla
alter table bulegoak add foreign key (id_asoziazioa) references asoziazioak(id_asoziazioa)
on update cascade on delete restrict;

-- Zenbat_material tabla
alter table zenbat_material add foreign key (id_materiala) references materiala(id_materiala)
on update cascade on delete restrict;

alter table zenbat_material add foreign key (id_asoziazioa) references asozioazioak(id_asoziazioa)
on update cascade on delete restrict;

-- Asoziazio_parte tabla
alter table asoziazio_parte add foreign key (id_asoziazioa) references aszioazioak(id_asoziazioa)
on update cascade on delete restrict;

alter table asoziazio_parte add foreign key (id_sozioa) references sozioak(id_sozioa)
on update cascade on delete restrict;

-- Zein_produktu tabla
alter table zein_produktu add foreign key (id_produktua) references produktuak(id_produktua)
on update cascade on delete restrict;

alter table zein_produktu add foreign key (id_asoziazioa) references asoziazioak(id_asoziazioa)
on update cascade on delete restrict;

-- Sozioak tabla
alter table sozioak add foreign key (id_zuzendaria) references sozioak(id_sozioa)
on update cascade on delete restrict;

-- Ze_ezti tabla
alter table ze_ezti add foreign key (id_sozioa) references sozioak(id_sozioa)
on update cascade on delete restrict;

alter table ze_ezti add foreign key (id_produktua) references produktuak(id_produktua)
on update cascade on delete restrict;

-- Eztia tabla
alter table eztia add foreign key (id_produktua) references produktuak(id_produktua)
on update cascade on delete restrict;

alter table eztia add foreign key (id_lorea) references loreak(id_lorea) -- ---------------------------------------- f
on update cascade on delete restrict;

-- Besteak tabla
alter table besteak add foreign key (id_produktua) references produktuak(id_produktua)
on update cascade on delete restrict;

alter table hymenoptera_europan add foreign key (id_produktua) references besteak(id_produktua)
on update cascade on delete restrict;

-- Hymenoptera_Europan tabla
alter table hymenoptera_europan add foreign key (id_produktua) references eztia(id_produktua)
on update cascade on delete restrict;

-- Zein_etsai tabla
alter table zein_etsai add foreign key (id_etsaia) references etsailak(id_etsaia)
on update cascade on delete restrict;

alter table zein_etsai add foreign key (id_hymenoptera) references hymenoptera_europan(id_hymenoptera)
on update cascade on delete restrict;

set foreign_key_checks=1;

