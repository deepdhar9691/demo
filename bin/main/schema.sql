create table station
(
   stationid varchar(100) not null,
   name varchar(255) , 
   hdenabled boolean ,
   callsign varchar(255) ,
   primary key(stationid)
);