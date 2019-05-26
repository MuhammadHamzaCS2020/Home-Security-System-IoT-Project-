

DROP TABLE IF EXISTS Devices;
 CREATE TABLE Devices(
  ID int NOT NULL AUTO_INCREMENT,
  IP varchar(50),
  PORT varchar(20),
  NAME varchar(50),
  PASSWORD varchar(50),
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS sensorValues;
 CREATE TABLE sensorValues(
  ID int NOT NULL AUTO_INCREMENT,
  DeviceName varchar(50),
  Motion varchar(50),
  Distence varchar(20),
  dateTime DATETIME,
  PRIMARY KEY (ID)
);








