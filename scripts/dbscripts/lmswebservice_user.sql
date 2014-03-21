create table LMSWEBSERVICE_USER (
USER_ID int identity(1,1),
USER_NM varchar(25) not null,
PASSWORD varchar(50) not null
primary key (user_id) )

insert into lmswebservice_user (USER_NM, PASSWORD) values ('banneruser', 'Qm5SQzB1ciQzJA==' )
insert into lmswebservice_user (USER_NM, PASSWORD) values ('IACUCuser', 'SUE2dTZLMHVyJmU=' )
insert into lmswebservice_user (USER_NM, PASSWORD) values ('UCSFuser', 'U0ZAMTFLMHVyJDNT' )


alter table LMSWEBSERVICE_USER
ADD offset int

alter table LMSWEBSERVICE_USER
ADD domain_id int