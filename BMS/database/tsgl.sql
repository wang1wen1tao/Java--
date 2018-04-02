/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/9/21 15:11:40                           */
/*==============================================================*/


drop table if exists book;

drop table if exists borrow;

drop table if exists reader;

drop table if exists user;

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   id                   varchar(50) not null,
   name                 varchar(50),
   type                 varchar(50),
   author               varchar(50),
   translator           varchar(50),
   publisher            varchar(50),
   publish_time         varchar(50),
   stock                int,
   price                double,
   primary key (id)
);

/*==============================================================*/
/* Table: borrow                                                */
/*==============================================================*/
create table borrow
(
   id                   int not null,
   book_id              varchar(50),
   reader_id            varchar(50),
   borrow_date          date,
   back_date            date,
   is_back              boolean,
   primary key (id)
);

/*==============================================================*/
/* Table: reader                                                */
/*==============================================================*/
create table reader
(
   id                   varchar(50) not null,
   name                 varchar(50),
   type                 varchar(50),
   sex                  varchar(4),
   max_num              int,
   days_num              int,
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null,
   name                 varchar(50),
   pass                 varchar(50),
   is_admin             smallint(1),
   primary key (id)
);

insert into user(id,name,pass,is_admin) values(1,"admin","admin",1);
insert into user(id,name,pass,is_admin) values(2,"user","user",0);