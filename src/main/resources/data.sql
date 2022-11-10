drop table if exists name CASCADE ;

drop table if exists person CASCADE ;
 create table person( 
	first_name varchar(255) not null, 
	last_name varchar(255) not null, 
	address varchar(255), 
	city varchar(255), 
	email varchar(255), 
	phone varchar(255), 
	zip varchar(255), 
	primary key (first_name, last_name)
);


drop table if exists firestation CASCADE ;
drop table if exists fire_station CASCADE ;
create table fire_station (
	address varchar(255) not null, 
	station varchar(255) not null, 
	primary key (address, station)
);

drop table if exists medicalrecord CASCADE ;
drop table if exists medical_record CASCADE ;
create table medical_record (
	first_name varchar(255) not null, 
	last_name varchar(255) not null, 
	birthdate date, 
	primary key (first_name, last_name)
);


drop table if exists medication CASCADE ;
create table medication (
	first_name varchar(255) not null, 
	last_name varchar(255) not null, 
	quantity varchar(255) not null, 
	name varchar(255) not null, 
	primary key (first_name, last_name, name)
);
alter table medication add constraint FK72d5v2o3gv89k2drh3we0hu98 foreign key (first_name, last_name) references medical_record;

drop table if exists allergie CASCADE ;
create table allergie (
	first_name varchar(255) not null, 
	last_name varchar(255) not null, 
	name varchar(255) not null
);
alter table allergie add constraint FKnofexed22b6hb0mrshxx55pbp foreign key (first_name, last_name) references medical_record;

