drop table T_POST if exists;

create table T_POST (ID bigint identity primary key,
                        THREADID varchar(9),
                        SUBJECT varchar(100),
                        BODY varchar(300) NOT NULL,
                        ACCOUNTNUMBER varchar(9));
                        
--ALTER TABLE T_POST ALTER COLUMN BALANCE SET DEFAULT 0.0;
