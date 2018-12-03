--DROP Table Customer;
CREATE TABLE Customer (
  username varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  name varchar(20), NOT NULL
  address varchar(60) NOT NULL,
  id INT primary key NOT NULL
);


-- --------------------------------------------------------
--DROP Table Demands;
CREATE TABLE Demands (
  Name varchar(20) NOT NULL,
  Address varchar(60) NOT NULL,
  Destination varchar(60) NOT NULL,
  Date date DEFAULT NULL,
  Time time DEFAULT NULL,
  Status varchar(15) NOT NULL,
  Price INT NOT NULL,
  id INT primary key NOT NULL
);


-- --------------------------------------------------------
--DROP Table Drivers;
CREATE TABLE Drivers (
  username varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  Registration varchar(10) NOT NULL,
  Name varchar(20) NOT NULL,
  PRIMARY KEY (Registration) NOT NULL
);


-- --------------------------------------------------------

--DROP Table Journey;
CREATE TABLE Journey (
  jid INT primary key NOT NULL
  id int, NOT NULL
  Destination varchar(60), NOT NULL
  Distance integer NOT NULL DEFAULT 1,
  Registration varchar(10) NOT NULL,
  Date date NOT NULL,
  Time time DEFAULT NULL
  Price INT NOT NULL,
);

CREATE TABLE Admin (
  username varchar(20), NOT NULL
  password varchar(20), NOT NULL
);


Alter table Journey add foreign key (id) references Customer;
Alter table Journey add foreign key (Registration) references Drivers;


