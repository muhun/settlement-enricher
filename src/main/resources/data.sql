DROP TABLE IF EXISTS settlement_message;
DROP TABLE IF EXISTS ssi_info;

CREATE TABLE ssi_info(
ssi_code varchar(200) primary key,
payer_account_number varchar(100),
payer_bank varchar(200),
receiver_account_number varchar(100),
receiver_bank varchar(200),
supporting_info varchar(500)
);

CREATE TABLE settlement_message(
trade_id varchar(100),
message_id uuid primary key,
ssi_code varchar(200) not null ,
currency varchar(3) not null,
amount decimal not null,
value_date date not null,
foreign key(ssi_code) references ssi_info(ssi_code)
);

INSERT INTO ssi_info(SSI_CODE, PAYER_ACCOUNT_NUMBER, PAYER_BANK, RECEIVER_ACCOUNT_NUMBER, RECEIVER_BANK, SUPPORTING_INFO)
VALUES('DBS_OCBC_1','05461368','DBSSGB2LXXX','438421','OCBCSGSGXXX','BNF:PAY CLIENT'),
('OCBC_DBS_1','438421','OCBCSGSGXXX','05461368','DBSSGB2LXXX','BNF:FFC-4697132'),
('OCBC_DBS_2','438421','OCBCSGSGXXX','05461369','DBSSSGSGXXX','BNF:FFC-482315'),
('DBS_SCB','185586','DBSSSGSGXXX','1868422','SCBLAU2SXXX','RFB:Test payment'),
('CITI_GS','00454983','CITIGB2LXXX','48486414','GSCMUS33XXX','');




