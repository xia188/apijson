### 拿到最新的APIJSON-Demo

git clone https://github.com/APIJSON/APIJSON-Demo

### 处理MySQL/single下的sql脚本，json格式不支持时使用mediumtext替代

apijson.hutool.SimpleTest.testMySQL()

### 复制APIJSON-Java-Server\APIJSONBoot-MultiDataSource\src\main\resources\static到apijson目录

### 处理报错：http://localhost:8080/h2/，打开h2操作界面

####

SELECT `table_name` AS `table_name`,`table_comment` AS `table_comment` FROM `information_schema`.`tables` WHERE  (  (`table_schema` IN ('apijson')) AND (`table_type` = 'BASE TABLE') AND (`table_name` IN (SELECT `name` FROM `apijson`.`Access`) )  )  ORDER BY `table_name` ASC  LIMIT 50

h2数据库table_comment对应的字段应该是remarks，因此调整Access表里alias=Table的记录，实际表名指向试图tables_view，然后再测试上面的sql能否成功执行。
create view information_schema.tables_view as select table_name,remarks as table_comment,table_schema,table_type from information_schema.tables;

创建视图之后可以查看一些，有问题的话就drop掉，然后重新创建。
select * from information_schema.tables_view;
drop view information_schema.tables_view;

#####

SELECT `column_name` AS `column_name`,`column_type` AS `column_type`,`is_nullable` AS `is_nullable`,`column_default` AS `column_default`,`column_comment` AS `column_comment` FROM `information_schema`.`columns` WHERE  (  (`table_schema` IN ('apijson')) AND (`table_name` = 'apijson_privacy')  )  LIMIT 100

column_type对应h2的data_type，column_comment对应h2的remarks。
create view information_schema.columns_view as select column_name,data_type as column_type,is_nullable,column_default,remarks as column_comment,table_schema,table_name from information_schema.columns;


