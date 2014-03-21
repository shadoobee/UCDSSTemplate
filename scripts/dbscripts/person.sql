USE LMSREPORTING

GO


CREATE TABLE PERSON(
PERSON_ID INT IDENTITY(1,1),
LAST_NAME VARCHAR(255) NOT NULL,
FIRST_NAME VARCHAR(255) NOT NULL,
UCNET_ID VARCHAR(255) NULL,
EMAIL VARCHAR(255) NULL,
SHIB_USER_ID VARCHAR(255) NULL,
LOCATION_ID  INT NOT NULL,
ROLE_ID INT NOT NULL,
PRIMARY KEY(PERSON_ID),
FOREIGN KEY(ROLE_ID) REFERENCES ROLE(ROLE_ID),
FOREIGN KEY(LOCATION_ID) REFERENCES LOCATION(LOCATION_ID)
)
GO
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Deherrera', 'Alan', 2,  null, 3, 'deherrera@berkeley.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Valerio', 'Kathleen',2,  null, 3, 'kvalerio@berkeley.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Cannon', 'Robert',2,  null, 3, 'rcannon@berkeley.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Reinman', 'Sondra',2,  null, 3, 'sondra@berkeley.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Bean', 'Tim',2,  null, 3, 'mtbean@berkeley.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Amundson', 'Ben',3,  null, 3, 'ben.amundson@ucsf.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Diettinger', 'Don',3, null, 3, 'don.diettinger@ucsf.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Hones', 'Luke',3, null, 3, 'luke.hones@ucsf.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Liddell', 'Katy',3, null, 3, 'katy.liddell@ucsf.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Green', 'Charles',13,  null, 3, 'charles.green@ucsfmedctr.org')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('David', 'Christopher',13,  null, 3, 'christopher.david@ucsfmedctr.org')


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Germino', 'Carolyn',4 , '1417738', 1, 'cbgermin@ucdavis.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Bair', 'Joe', 4, '1442250', 1, 'jmbair@ucdavis.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Storm', 'Ed', 4, null, 3, 'erstorm@ucdavis.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Crabtree', 'Kelly',4, null, 3, 'kacrabtree@ucdavis.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Cox', 'Diana', 4, null, 3, 'dicox@ucdavis.edu' )
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Hussain', 'Safa',4,  null, 3, 'smhussain@ucdavis.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Tomic', 'Stefan', 4,  null, 3, 'sttomic@ucdavis.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Lathe', 'Chad', 4,  null, 3, 'clathe@ucdavis.edu' )

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Wu', 'Albert',5,   null, 3, 'albertwu@ucla.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Mahabalagiri', 'Datta', 5,  null, 3, 'datta@ais.ucla.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Zomorrodian', 'Shadi', 5,  null, 3, 'fzomorrodian@chr.ucla.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Powell', 'Jewel',5, null, 3, 'jewelpowell@chr.ucla.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Ogi', 'Mitch',5,  null, 3, 'mitch@chr.ucla.edu')


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Wong', 'Judith',14,  null, 3, 'jkwong@mednet.ucla.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Bright', 'Josh', 6, null, 3, 'josh.bright@ucr.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Terry', 'Nancy',6, null, 3, 'nancy.terry@ucr.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Beckham', 'Christy',7, null, 3, 'cbeckham@ucsd.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Chen', 'Chaozi',7,  null, 3, 'czchen@ucsd.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Kahlor', 'Tim', 7,  null, 3, 'tkahlor@ucsd.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Elder', 'Matt',7,  null, 3, 'm1elder@ad.ucsd.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Sylwestrzak', 'Marc', 15,  null, 3, 'msylwestrzak@ucsd.edu')


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Councilman', 'Scott',12,  null, 3, 'scouncil@ucsc.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Gray', 'Wes', 12,  null, 3, 'wwgray@ucsc.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Beld', 'Tony', 12,  null, 3, 'tbeld@ucsc.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Crawford', 'Melinda',8, null, 3, 'Melinda.Crawford@hr.ucsb.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Kumamoto', 'Bob', 9,  null, 3, 'bob.kumamoto@uci.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Heck', 'Kristie',9,  null, 3, 'kheck@uci.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Kussmann', 'Ying', 9, null, 3, 'yingf@uci.edu' )


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Vo', 'Kimberly',16, null, 3, 'ktvo@uci.edu')


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Gonzalez', 'Jody', 10,  null, 3, 'jgonzalez37@ucmerced.edu')


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Dedlow', 'Mark',11, null, 3, 'mtdedlow@lbl.gov')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Jayabalan', 'Karthik',11, null, 3, 'kjayabalan@lbl.gov')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Jadhav', 'Sachin',11, null, 3, 'sjjadhav@lbl.gov')


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Thompson', 'Christy', 1,  null, 3, 'christy.thompson@ucop.edu')


insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Cheung Hill', 'Esther',1,  null, 2, 'esther.cheung@ucop.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Emory', 'Sarah', 1,  null, 2, 'sarah.emory@ucop.edu')
insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Jiang', 'Sylvia',1,  null, 2, 'sylvia.jiang@ucop.edu')

insert into person (LAST_NAME, FIRST_NAME, LOCATION_ID, UCNET_ID, ROLE_ID, EMAIL) values ('Limited', 'User',4, null, 4, 'noemail@ucdavis.edu')


GO
