use farma;
select * from stroj;
select * from tankovanie;
select * from oprava;
select * from zviera;
select * from pole;

insert into stroj (vyrobca, typ, kategoria, cena, datum) values ('Zetor','Super 50', 'traktor', 50000, '2017-09-01');
insert into stroj (vyrobca, typ, kategoria, cena, datum) values ('Zetor','25 A', 'traktor', 28500, '2013-06-16');
insert into tankovanie (stroj_id,mnozstvo, datum) values (1, 40, '2017-06-20');
insert into tankovanie (stroj_id,mnozstvo, datum) values (2, 35, '2017-06-21');
insert into oprava (stroj_id, datum, cena, porucha, popis) values(1, '2017-01-20', 10000, 'prasknutý blok motora', 'výmena bloku motora, výmena oleja');

DELETE FROM zviera WHERE plemeno ='slovenský strakatý';

insert into zviera values(12345, 'dobytok', 'slovenský strakatý', 'f', '2015-03-15', '2015-03-15', 0);

select * from zviera;
select zviera.registracne_cislo as 'zRegistracneCislo', zviera.druh as 'zDruh', zviera.plemeno as 'zPlemeno', zviera.pohlavie as 'zPohlavie', datum_narodenia as 'zDatumNarodenia', zviera.datum_nadobudnutia as 'zDatumNadobudnutia', zviera.kupna_cena as 'zKupnaCena'  from zviera;
-- MySQL Workbench Forward Engineering
