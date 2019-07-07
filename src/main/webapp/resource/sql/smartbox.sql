create database ssm_smartbox;

use ssm_smartbox;

create table user(id char(11) primary key, password char(20) not null, name char(20) not null) default charset=utf8;

create table register(phoneNumber char(11) primary key, verification int) default charset=utf8;

create table box(id int primary key, status int);

create table medicine_order(
id char(20) primary key,
status int,
bid int,
verification int,
medicine varchar(100),
uid char(11) character set utf8 not null,
update_time timestamp not null default current_timestamp on update current_timestamp,
create_time timestamp not null default current_timestamp,
foreign key (bid) references box(id),
foreign key (uid) references user(id));

create table history_order(
id char(20)primary key,
medicine varchar(100),
uid char(11) character set utf8 not null,
create_time timestamp not null,
finish_time timestamp not null default current_timestamp,
foreign key(uid) references user(id)
);