drop table if exists tbl_transactions;
CREATE TABLE IF NOT EXISTS tbl_transactions
  (
     trans_id             INT NOT NULL auto_increment,
     trans_date           DATE,
     description          VARCHAR(255),
     original_description VARCHAR(255),
     amount               DECIMAL(10, 2),
     transtype            VARCHAR(255),
     segment              VARCHAR(255),
     category             VARCHAR(255),
     account_name         VARCHAR(255),
     labels               VARCHAR(255),
     notes                VARCHAR(255),
     PRIMARY KEY (trans_id)
  );

load data local infile '/Users/kennethlin/transactions_1706.csv' into table mysql.tbl_transactions
fields
ENCLOSED BY '"'
terminated by ','
LINES TERMINATED BY '\r'
IGNORE 1 LINES
(trans_date,description, original_description, amount, transtype,category,account_name,labels,notes)
;

--select category, sum(amount) from tbl_transactions group by category order by sum(amount);
--select max(t.trans_date), min(t.trans_date) from tbl_transactions t where t.trans_date between date('2017-11-01 00:00:00') and date('2017-11-30 00:00:00') ;

