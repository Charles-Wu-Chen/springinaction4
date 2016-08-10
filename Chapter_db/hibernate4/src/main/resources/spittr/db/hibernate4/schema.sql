/*for mysql*/
drop table if exists spittle;
drop table if exists spitter;
create table spitter (
  id INT         NOT NULL AUTO_INCREMENT,
  username varchar(25) not null,
  password varchar(25) not null,
  fullName varchar(100) not null,
  email varchar(50) not null,
  updateByEmail boolean not null,
  PRIMARY KEY (`ID`)
);

create table spittle (
  id INT         NOT NULL AUTO_INCREMENT,
  spitter integer not null,
  message varchar(2000) not null,
  postedTime datetime not null,
  PRIMARY KEY (`ID`),
  foreign key (spitter) references spitter(id)
);
