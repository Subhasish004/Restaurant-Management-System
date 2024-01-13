create database hotelmanagement;
use hotelmanagement;
create table restaurant(
rname varchar(255) primary key,
address varchar(255),
phone varchar(20),
capacity int);
create table rtable(
tableid int primary key ,
capacity int,
available boolean ,
rname varchar(255),
FOREIGN KEY (rname) REFERENCES restaurant(rname) );
create table reservation(
id int AUTO_INCREMENT primary key ,
rdate date,
rtime varchar(255),
partysize int,
customername varchar(255),
phonenumber varchar(20),
tableid int ,
FOREIGN KEY (tableid) REFERENCES rtable(tableid) );




