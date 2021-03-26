DROP DATABASE ITSS;

CREATE DATABASE ITSS;

USE ITSS;
Set global local_infile=1;

CREATE TABLE stations(
    SID int primary key,
    name varchar(20),
    address varchar(100),
    total_dock int
);
LOAD DATA LOCAL INFILE 'C:/Users/Admin/Desktop/Software_Development/ITSS/ict1.k62s.20201-06/Database/data/ITSS - stations.csv' 
INTO TABLE stations
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

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
LOAD DATA LOCAL INFILE 'C:/Users/Admin/Desktop/Software_Development/ITSS/ict1.k62s.20201-06/Database/data/ITSS - bikes.csv' 
INTO TABLE bikes
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

CREATE TABLE users(
    UID int primary key,
    name varchar(30),
    phone varchar(15),
    email varchar(20),
    gender varchar(10)
);
LOAD DATA LOCAL INFILE 'C:/Users/Admin/Desktop/Software_Development/ITSS/ict1.k62s.20201-06/Database/data/ITSS - users.csv' 
INTO TABLE users
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

CREATE TABLE accounts(
    AID int primary key, 
    UID int references users(UID) on delete cascade on update cascade,
    card_number varchar(20)
);
LOAD DATA LOCAL INFILE 'C:/Users/Admin/Desktop/Software_Development/ITSS/ict1.k62s.20201-06/Database/data/ITSS - accounts.csv' 
INTO TABLE accounts
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

CREATE TABLE records(
    RID int primary key,
    AID int references accounts(AID) on delete cascade on update cascade,
    BID int references bikes(BID) on delete cascade on update cascade,
    start_time varchar(50), 
    end_time varchar(50)
);
LOAD DATA LOCAL INFILE 'C:/Users/Admin/Desktop/Software_Development/ITSS/ict1.k62s.20201-06/Database/data/ITSS - records.csv' 
INTO TABLE records
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

CREATE TABLE cards(
    CID int primary key,
    card_number varchar(20), 
    balance bigint,
    issuing_bank varchar(20), 
    expiration_date varchar(50)
);
LOAD DATA LOCAL INFILE 'C:/Users/Admin/Desktop/Software_Development/ITSS/ict1.k62s.20201-06/Database/data/ITSS - cards.csv' 
INTO TABLE cards
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;
