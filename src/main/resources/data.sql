drop table if exists person;

create table person (
  id int auto_increment  primary key,
  first_name varchar(250) not null,
  last_name varchar(250) not null,
  age varchar(50) default null,
  favourite_colour varchar(250) default null
);