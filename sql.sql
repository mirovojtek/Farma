
drop database if exists farma;
use farma;
select * from stroj;
select * from tankovanie where stroj_id = 2;
select * from oprava;
select * from zviera;
select * from pole;

select tankovanie.id as 'tankovanie', tankovanie.stroj_id as 'strojId', tankovanie.mnozstvo as 'mnozstvo', tankovanie.datum as 'datum' from tankovanie where stroj_id=2;

select stroj.id as 'sId', stroj.vyrobca as 'sVyrobca', stroj.typ as 'sTyp', stroj.kategoria as 'sKategoria', stroj.datum_nadobudnutia as 'sDatumNadobudnutia', stroj.cena as 'sCena' from stroj;

delete from tankovanie where id=1;
delete from stroj where stroj.id =1;
DELETE FROM zviera WHERE registracne_cislo = 12345;
select * from zviera where registracne_cislo = 12345;

drop database if exists farma;
