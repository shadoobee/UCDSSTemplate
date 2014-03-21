use LMSReporting
GO

drop table ROLE;
go 
create table ROLE (
ROLE_ID INT IDENTITY(1,1),
ROLE_NAME VARCHAR(25) NOT NULL,
ROLE_DESC VARCHAR(255) NULL,
PRIMARY KEY(ROLE_iD)
);
GO
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('Admin', 'Administrator gives access and can view all')
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('SuperUser', 'Super User can see all domains')
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('User', 'User can see a domain and its subdomains')
INSERT INTO ROLE (ROLE_NAME, ROLE_DESC) VALUES ('LimitedUser', 'User can see a domain and its subdomains but has limited activity codes')