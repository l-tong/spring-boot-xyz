drop database system1;
drop database system2;
create database system1;
CREATE TABLE `system1`.`SYSTEM1_USER` (
  `SYSTEM1_USER_ID` INT NOT NULL,
  `SYSTEM1_DISPLAYNAME` VARCHAR(255) NULL,
  PRIMARY KEY (`SYSTEM1_USER_ID`));
  
CREATE TABLE `system1`.`SYSTEM1_USER_GROUP_MEMBERSHIP` (
  `SYSTEM1_USER_ID` INT NOT NULL,
  `SYSTEM1_GROUP_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SYSTEM1_USER_ID`, `SYSTEM1_GROUP_NAME`),
  CONSTRAINT `fk_SYSTEM1_USER_GROUP_MEMBERSHIP_1`
    FOREIGN KEY (`SYSTEM1_USER_ID`)
    REFERENCES `system1`.`SYSTEM1_USER` (`SYSTEM1_USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

create database system2;
CREATE TABLE `system2`.`SYSTEM2_USER` (
  `SYSTEM2_USER_ID` INT NOT NULL,
  `SYSTEM2_DISPLAYNAME` VARCHAR(255) NULL,
  PRIMARY KEY (`SYSTEM2_USER_ID`));
  
CREATE TABLE `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` (
  `SYSTEM2_USER_ID` INT NOT NULL,
  `SYSTEM2_GROUP_NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SYSTEM2_USER_ID`, `SYSTEM2_GROUP_NAME`),
  CONSTRAINT `fk_SYSTEM2_USER_GROUP_MEMBERSHIP_1`
    FOREIGN KEY (`SYSTEM2_USER_ID`)
    REFERENCES `system2`.`SYSTEM2_USER` (`SYSTEM2_USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

insert into `system2`.`SYSTEM2_USER` values (1, 'Test User1'); 
insert into `system2`.`SYSTEM2_USER` values (2, 'Test User2'); 
insert into `system2`.`SYSTEM2_USER` values (3, 'Test User3'); 
insert into `system2`.`SYSTEM2_USER` values (4, 'Test User4'); 
insert into `system2`.`SYSTEM2_USER` values (5, 'Test User1'); 
insert into `system2`.`SYSTEM2_USER` values (6, 'Test User1');

insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (1, 'GROUP1');
insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (1, 'GROUP2');
insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (2, 'GROUP1');
insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (3, 'GROUP1');
insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (4, 'GROUP1');
insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (5, 'GROUP1');
insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (6, 'GROUP1');
insert into `system2`.`SYSTEM2_USER_GROUP_MEMBERSHIP` values (6, 'GROUP2');

insert into `system1`.`SYSTEM1_USER` values (1, 'Test User1'); 
insert into `system1`.`SYSTEM1_USER` values (2, 'Test-U User2'); 
insert into `system1`.`SYSTEM1_USER` values (3, 'Test User3'); 
insert into `system1`.`SYSTEM1_USER` values (4, 'Test User4'); 
insert into `system1`.`SYSTEM1_USER` values (5, 'Test User1');

insert into `system1`.`SYSTEM1_USER_GROUP_MEMBERSHIP` values (1, 'GROUP1');
insert into `system1`.`SYSTEM1_USER_GROUP_MEMBERSHIP` values (1, 'GROUP2');
insert into `system1`.`SYSTEM1_USER_GROUP_MEMBERSHIP` values (2, 'GROUP1');
insert into `system1`.`SYSTEM1_USER_GROUP_MEMBERSHIP` values (3, 'GROUP2');
insert into `system1`.`SYSTEM1_USER_GROUP_MEMBERSHIP` values (4, 'GROUP1');
insert into `system1`.`SYSTEM1_USER_GROUP_MEMBERSHIP` values (5, 'GROUP1');