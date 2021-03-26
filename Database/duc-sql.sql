DROP DATABASE ITSS;

CREATE DATABASE ITSS;

USE ITSS;

CREATE TABLE stations(
    SID int primary key,
    name varchar(20),
    address varchar(100),
    total_dock int
);

CREATE TABLE bikes(
    BID int primary key auto_increment,
    name varchar(50), 
    type varchar(50),
    weight float, 
    license_plate varchar(20),
    manufacturing_date varchar(50),
    producer varchar(20), 
    SID int references stations(SID) on delete cascade on update cascade
);


CREATE TABLE users(
    UID int primary key,
    name varchar(30),
    phone varchar(15),
    email varchar(20),
    gender varchar(10)
);


CREATE TABLE accounts(
    AID int primary key, 
    UID int references users(UID) on delete cascade on update cascade,
    card_number varchar(20)
);


CREATE TABLE records(
    RID int primary key,
    AID int references accounts(AID) on delete cascade on update cascade,
    BID int references bikes(BID) on delete cascade on update cascade,
    start_time varchar(50), 
    end_time varchar(50)
);


CREATE TABLE cards(
    CID int primary key,
    card_number varchar(20), 
    balance bigint,
    issuing_bank varchar(20), 
    expiration_date varchar(50)
);

