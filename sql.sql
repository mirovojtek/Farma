
drop database if exists farma;
use farma;
select * from stroj;
select * from tankovanie;
select * from financie;
select * from pole;
SELECT financie.id AS 'fId', financie.datum AS 'fDatum', financie.suma AS 'fSuma', financie.typ AS 'fTyp', financie.popis AS 'fPopis' from farma.financie;
DELETE FROM pole WHERE pole.id =1;
UPDATE pole SET parcela = '149', vymera = 1000, typ = 'lúka', datum_nadobudnutia = '2015-10-10', cena = 500 WHERE id = 1;
select * from financie;
delete from zviera where id = 15;
select * from oprava;
select * from zviera;
select * from pole;

select financie.id as 'fId', financie.datum as 'fDatum', financie.suma as 'fSuma', financie.typ as 'fTyp', financie.popis as 'fPopis' from financie;


SELECT DISTINCT vyrobca AS 'vyrobca' FROM farma.stroj;
SELECT DISTINCT typ AS 'typ' FROM farma.stroj WHERE vyrobca='Zetor';

select tankovanie.id as 'tId', tankovanie.stroj_id as 'tStrojId', tankovanie.mnozstvo as 'tMnozstvo', tankovanie.datum as 'tDatum' from tankovanie;


-- //Stroj findById(int id)
select * from stroj;
select tankovanie.id as 'tId', tankovanie.stroj_id as 'tStrojId', tankovanie.mnozstvo as 'tMnozstvo', tankovanie.datum as 'tDatum' from tankovanie;
-- select oprava.id as 'oId', oprava.stroj_id as 'oStrojId', oprava.datum as 'oDatum', oprava.cena as 'oCena', oprava.porucha as 'oPorucha', oprava.popis as 'oPopis' from oprava where oprava.stroj_id=2;
DELETE FROM tankovanie WHERE tankovanie.stroj_id =2;
select * from tankovanie;

select tankovanie.id as 'tankovanie', tankovanie.stroj_id as 'strojId', tankovanie.mnozstvo as 'mnozstvo', tankovanie.datum as 'datum' from tankovanie where stroj_id=2;
select stroj.id as 'sId', stroj.vyrobca as 'sVyrobca', stroj.typ as 'sTyp', stroj.kategoria as 'sKategoria', stroj.datum_nadobudnutia as 'sDatumNadobudnutia', stroj.cena as 'sCena' from stroj;
alter table stroj add column popis varchar(200);

delete from tankovanie where id=1;
delete from stroj where stroj.id =1;
DELETE FROM zviera WHERE registracne_cislo = 12345;
select * from zviera where registracne_cislo = 12345;

drop database if exists farma;
