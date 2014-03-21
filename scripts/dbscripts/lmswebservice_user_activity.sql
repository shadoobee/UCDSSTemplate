create table LMSWEBSERVICE_USER_ACTIVITY(
LMSWEBSERVICE_USER_ACTIVITY_ID INT NOT NULL IDENTITY(1,1),
USER_ID int not null,
ACTIVITY_CD varchar(25) not null,
PRIMARY KEY (LMSWEBSERVICE_USER_ACTIVITY_ID),
foreign key (USER_ID) references lmswebservice_user(USER_ID))

insert into LMSWEBSERVICE_USER_ACTIVITY (USER_ID,ACTIVITY_CD) values (1, 'DAC-BA0195-ECO');
insert into LMSWEBSERVICE_USER_ACTIVITY (USER_ID,ACTIVITY_CD) values (1, 'DAC-SIS015');
insert into LMSWEBSERVICE_USER_ACTIVITY (USER_ID,ACTIVITY_CD) values (1, 'SIS-BannerForms');
insert into LMSWEBSERVICE_USER_ACTIVITY (USER_ID,ACTIVITY_CD) values (2, 'DACS-ACU101');