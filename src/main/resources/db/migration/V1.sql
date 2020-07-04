create table station
(
   stationid varchar(100) not null,
   name varchar(255) , 
   hdenabled boolean ,
   callsign varchar(255) ,
   primary key(stationid)
);

insert into station(stationid,name,hdenabled,callsign) values('123','deepak','true','test1');
insert into station(stationid,name,hdenabled,callsign) values('456','jai','true','test2');
insert into station(stationid,name,hdenabled,callsign) values('789','jai','true','test3');