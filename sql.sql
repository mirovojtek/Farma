
drop database if exists farma;
use farma;
select * from stroj;
select * from tankovanie;
select * from oprava;
select * from zviera;
select * from pole;

DELETE FROM zviera WHERE registracne_cislo = 12345;
select * from zviera where registracne_cislo = 12345;

drop database if exists farma;
