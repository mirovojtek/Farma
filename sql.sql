
drop database if exists farma;
use farma;
select * from stroj;
select * from tankovanie;

delete from zviera where id = 15;
select * from oprava;
select * from zviera;
select * from pole;

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


delete from tankovanie where id=1;
delete from stroj where stroj.id =1;
DELETE FROM zviera WHERE registracne_cislo = 12345;
select * from zviera where registracne_cislo = 12345;

drop database if exists farma;
