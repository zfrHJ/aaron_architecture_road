#mysql 原生如下方式。每条sql语句执行，大约0.008s ,总共 15* 0.008s
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL);



#mybatis-plus for循环语句，如下格式。这个语句的时间比上面的慢很多。总共0.009s
INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL)
,('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL)
,('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL)
,('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL),('58964', '1', '2019-07-24 09:59:56', NULL)
,('58964', '1', '2019-07-24 09:59:56', NULL);