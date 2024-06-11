CREATE SCHEMA IF NOT EXISTS apijson;
SET SCHEMA apijson;

DROP TABLE IF EXISTS `Access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Access` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `debug` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否为调试表，只允许在开发环境使用，测试和线上环境禁用',
  `name` varchar(50) NOT NULL COMMENT '实际表名，例如 apijson_user',
  `alias` varchar(20) DEFAULT NULL COMMENT '外部调用的表别名，例如 User',
  `get` varchar(100) NOT NULL DEFAULT '["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]' COMMENT '允许 get 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]\n用 JSON 类型不能设置默认值，反正权限对应的需求是明确的，也不需要自动转 JSONArray。\nTODO: 直接 LOGIN,CONTACT,CIRCLE,OWNER 更简单，反正是开发内部用，不需要复杂查询。',
  `head` varchar(100) NOT NULL DEFAULT '["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]' COMMENT '允许 head 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]',
  `gets` varchar(100) NOT NULL DEFAULT '["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]' COMMENT '允许 gets 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]',
  `heads` varchar(100) NOT NULL DEFAULT '["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]' COMMENT '允许 heads 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]',
  `post` varchar(100) NOT NULL DEFAULT '["OWNER", "ADMIN"]' COMMENT '允许 post 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]',
  `put` varchar(100) NOT NULL DEFAULT '["OWNER", "ADMIN"]' COMMENT '允许 put 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]',
  `delete` varchar(100) NOT NULL DEFAULT '["OWNER", "ADMIN"]' COMMENT '允许 delete 的角色列表，例如 ["LOGIN", "CONTACT", "CIRCLE", "OWNER"]',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `detail` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `alias_UNIQUE` (`alias`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='权限配置(必须)';

INSERT INTO `access` VALUES ('1','0','Access',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2019-07-21 12:21:36',null);
INSERT INTO `access` VALUES ('2','0','Table',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:14',null);
INSERT INTO `access` VALUES ('3','0','Column',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:14',null);
INSERT INTO `access` VALUES ('4','0','Function',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:15',null);
INSERT INTO `access` VALUES ('5','0','Request',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:14',null);
INSERT INTO `access` VALUES ('6','0','Response',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:15',null);
INSERT INTO `access` VALUES ('7','0','Document',null,'["LOGIN", "ADMIN"]','["LOGIN", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2018-11-28 16:38:15',null);
INSERT INTO `access` VALUES ('8','0','TestRecord',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2018-11-28 16:38:15',null);
INSERT INTO `access` VALUES ('9','0','Test',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:15',null);
INSERT INTO `access` VALUES ('10','1','PgAttribute',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:14',null);
INSERT INTO `access` VALUES ('11','1','PgClass',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:14',null);
INSERT INTO `access` VALUES ('12','0','Login',null,'[]','[]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[ "ADMIN"]','[ "ADMIN"]','["ADMIN"]','2018-11-28 16:29:48',null);
INSERT INTO `access` VALUES ('13','0','Verify',null,'[]','[]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[ "ADMIN"]','["ADMIN"]','2018-11-28 16:29:48',null);
INSERT INTO `access` VALUES ('14','0','apijson_user','User','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN","LOGIN","OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["ADMIN"]','2018-11-28 16:28:53',null);
INSERT INTO `access` VALUES ('15','0','apijson_privacy','Privacy','[]','[]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["UNKNOWN","LOGIN","OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["ADMIN"]','2018-11-28 16:29:48',null);
INSERT INTO `access` VALUES ('16','0','Moment',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2018-11-28 16:29:19',null);
INSERT INTO `access` VALUES ('17','0','Comment',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2018-11-28 16:29:19',null);
INSERT INTO `access` VALUES ('19','1','SysTable',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2019-10-04 01:01:20',null);
INSERT INTO `access` VALUES ('20','1','SysColumn',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2019-10-04 01:01:20',null);
INSERT INTO `access` VALUES ('21','1','ExtendedProperty',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2019-10-04 01:33:45',null);
INSERT INTO `access` VALUES ('22','1','Random',null,'["LOGIN", "ADMIN"]','["LOGIN", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["LOGIN", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2019-12-01 15:13:13',null);
INSERT INTO `access` VALUES ('23','1','Swagger',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','[]','[]','[]','2018-11-28 16:38:15',null);
INSERT INTO `access` VALUES ('24','0','Method',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["LOGIN", "ADMIN"]','["OWNER", "ADMIN"]','2018-11-28 16:38:15',null);
INSERT INTO `access` VALUES ('25','1','Input',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2020-01-10 07:12:49',null);
INSERT INTO `access` VALUES ('26','1','Device',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2020-01-10 07:12:49',null);
INSERT INTO `access` VALUES ('27','1','System',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2020-01-10 07:12:49',null);
INSERT INTO `access` VALUES ('28','1','Flow',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2020-01-10 07:12:49',null);
INSERT INTO `access` VALUES ('29','1','Output',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2020-01-10 07:12:49',null);
INSERT INTO `access` VALUES ('30','1','ViewTable',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2021-01-20 14:51:43','MySQL View 支持多张表合并为一张表，这个示例 View 的创建 SQL 为 CREATE VIEW sys.`ViewTable` AS SELECT C.id as `commentId`, C.toId, C.momentId, C.content,  U.* FROM sys.Comment AS C INNER JOIN sys.apijson_user AS U ON U.id = C.userId; 初测正则匹配等条件单表查询、与其它表关联查询、与其它表 JOIN 都和普通的表用起来没有大的区别，目前发现的问题两个是： 1.作为 ViewTable 的表中有同名字段的话要把部分字段取别名，例如 Comment 和 apijson_user 都有 id，这里就用 C.id as `commentId` 取了别名避免冲突；2.APIAuto 不能显示 ViewTable 的表注释和字段注释');
INSERT INTO `access` VALUES ('38','0','Script',null,'["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["UNKNOWN", "LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["LOGIN", "CONTACT", "CIRCLE", "OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','["OWNER", "ADMIN"]','2021-01-20 14:51:43','MySQL View 支持多张表合并为一张表，这个示例 View 的创建 SQL 为 CREATE VIEW sys.`ViewTable` AS SELECT C.id as `commentId`, C.toId, C.momentId, C.content,  U.* FROM sys.Comment AS C INNER JOIN sys.apijson_user AS U ON U.id = C.userId; 初测正则匹配等条件单表查询、与其它表关联查询、与其它表 JOIN 都和普通的表用起来没有大的区别，目前发现的问题两个是： 1.作为 ViewTable 的表中有同名字段的话要把部分字段取别名，例如 Comment 和 apijson_user 都有 id，这里就用 C.id as `commentId` 取了别名避免冲突；2.APIAuto 不能显示 ViewTable 的表注释和字段注释');

DROP TABLE IF EXISTS `Function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Function` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT,
  `debug` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否为调试表，只允许在开发环境使用，测试和线上环境禁用',
  `userId` bigint(15) NOT NULL COMMENT '管理员用户Id',
  `name` varchar(50) NOT NULL COMMENT '方法名',
  `arguments` varchar(100) DEFAULT NULL COMMENT '参数列表，每个参数的类型都是 String。\n用 , 分割的字符串 比 [JSONArray] 更好，例如 array,item ，更直观，还方便拼接函数。',
  `demo` varchar(1000) NOT NULL COMMENT '可用的示例。\nTODO 改成 call，和返回值示例 back 对应。',
  `detail` varchar(1000) NOT NULL COMMENT '详细描述',
  `type` varchar(50) NOT NULL DEFAULT 'Object' COMMENT '返回值类型。TODO RemoteFunction 校验 type 和 back',
  `version` tinyint(4) NOT NULL DEFAULT '0' COMMENT '允许的最低版本号，只限于GET,HEAD外的操作方法。\nTODO 使用 requestIdList 替代 version,tag,methods',
  `tag` varchar(20) DEFAULT NULL COMMENT '允许的标签.\nnull - 允许全部\nTODO 使用 requestIdList 替代 version,tag,methods',
  `methods` varchar(50) DEFAULT NULL COMMENT '允许的操作方法。\nnull - 允许全部\nTODO 使用 requestIdList 替代 version,tag,methods',
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `back` varchar(45) DEFAULT NULL COMMENT '返回值示例',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='远程函数。强制在启动时校验所有demo是否能正常运行通过';

INSERT INTO `function` VALUES ('3', '0', '0', 'countArray', 'array', '{"array": [1, 2, 3]}', '获取数组长度。没写调用键值对，会自动补全 "result()": "countArray(array)"', 'Object', '0', null, null, '2018-10-13 16:23:23', null);
INSERT INTO `function` VALUES ('4', '0', '0', 'countObject', 'object', '{"object": {"key0": 1, "key1": 2}}', '获取对象长度。', 'Object', '0', null, null, '2018-10-13 16:23:23', null);
INSERT INTO `function` VALUES ('5', '0', '0', 'isContain', 'array,value', '{"array": [1, 2, 3], "value": 2}', '判断是否数组包含值。', 'Object', '0', null, null, '2018-10-13 16:23:23', null);
INSERT INTO `function` VALUES ('6', '0', '0', 'isContainKey', 'object,key', '{"key": "id", "object": {"id": 1}}', '判断是否对象包含键。', 'Object', '0', null, null, '2018-10-13 16:30:31', null);
INSERT INTO `function` VALUES ('7', '0', '0', 'isContainValue', 'object,value', '{"value": 1, "object": {"id": 1}}', '判断是否对象包含值。', 'Object', '0', null, null, '2018-10-13 16:30:31', null);
INSERT INTO `function` VALUES ('8', '0', '0', 'getFromArray', 'array,position', '{"array": [1, 2, 3], "result()": "getFromArray(array,1)"}', '根据下标获取数组里的值。position 传数字时直接作为值，而不是从所在对象 request 中取值', 'Object', '0', null, null, '2018-10-13 16:30:31', null);
INSERT INTO `function` VALUES ('9', '0', '0', 'getFromObject', 'object,key', '{"key": "id", "object": {"id": 1}}', '根据键获取对象里的值。', 'Object', '0', null, null, '2018-10-13 16:30:31', null);
INSERT INTO `function` VALUES ('13', '0', '0', 'getWithDefault', 'value,defaultValue', '{"value": null, "defaultValue": 1}', '如果 value 为 null，则返回 defaultValue', 'Object', '0', null, null, '2019-08-20 23:26:36', null);
INSERT INTO `function` VALUES ('14', '0', '0', 'removeKey', 'key', '{"key": "s", "key2": 2}', '从对象里移除 key', 'Object', '0', null, null, '2019-08-20 23:26:36', null);
INSERT INTO `function` VALUES ('15', '0', '0', 'getFunctionDemo', null, '{}', '获取远程函数的 Demo', 'Object', '0', null, null, '2019-08-20 23:26:36', null);
INSERT INTO `function` VALUES ('16', '0', '0', 'getFunctionDetail', null, '{}', '获取远程函数的详情', 'Object', '0', null, null, '2019-08-20 23:26:36', null);
INSERT INTO `function` VALUES ('17', '0', '0', 'getMethodArguments', 'methodArgs', '{"methodArgs": "methodArgs"}', '获取方法参数', 'Object', '0', null, null, '2020-04-06 01:44:07', null);
INSERT INTO `function` VALUES ('19', '0', '0', 'getMethodRequest', null, '{}', '获取方法请求', 'Object', '0', null, null, '2020-04-06 01:44:07', null);

