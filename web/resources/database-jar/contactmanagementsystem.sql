CREATE TABLE IF NOT EXISTS employee
(
	empId int AUTO_INCREMENT Primary Key,
	empEmailId varchar(78) unique,
	password varchar(30)
);

CREATE TABLE IF NOT EXISTS category
(
	categoryId int AUTO_INCREMENT Primary Key,
	categoryName varchar(15)
);

CREATE TABLE IF NOT EXISTS contacts (
  contactId int(11) NOT NULL AUTO_INCREMENT,
  firstName varchar(30) NOT NULL,
  lastName varchar(30) NOT NULL,
  email varchar(78) DEFAULT NULL,
  phoneNo varchar(20) DEFAULT NULL,
  companyName varchar(100) DEFAULT NULL,
  companyLoc varchar(50) DEFAULT NULL,
  designation varchar(120) DEFAULT NULL,
  linkedInUrl varchar(256) DEFAULT NULL,
  categoryId int DEFAULT NULL,
  PRIMARY KEY (contactId),
  FOREIGN KEY(categoryId) references category(categoryId)
) ;

CREATE TABLE IF NOT EXISTS contactList (
  contactListId int(11) NOT NULL AUTO_INCREMENT,
  empId int(11),
  contactId int(11),
  PRIMARY KEY (contactListId),
  FOREIGN KEY (empId) References employee(empId),
  FOREIGN KEY(contactId) References contacts(contactId)
); 

CREATE TABLE IF NOT EXISTS contactrelation (
  contactRelationId int(11) NOT NULL AUTO_INCREMENT,
  contactListId int(11),
  nickName varchar(30),
  rating int(11) DEFAULT NULL,
  notes varchar(300) DEFAULT NULL,
  personalMsg varchar(300),
  PRIMARY KEY (contactRelationId),
  FOREIGN KEY (contactListId) References contactList(contactListId)
  
);

CREATE TABLE IF NOT EXISTS interests(
  interestId int(11) NOT NULL AUTO_INCREMENT,
  interestName varchar(50) DEFAULT NULL,
  PRIMARY KEY (interestId)
);

CREATE TABLE IF NOT EXISTS interestbridge (
  IB_id int(11) NOT NULL AUTO_INCREMENT,
  interestId int(11),
  contactId int(11),
  PRIMARY KEY (IB_id),
  FOREIGN KEY (interestId) References interests(interestId),
  FOREIGN KEY (contactId)  References contacts(contactId)
 );

