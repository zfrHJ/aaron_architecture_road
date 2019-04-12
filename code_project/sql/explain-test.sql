-- 测试explain的脚本
DROP TABLE IF EXISTS `actor`;
CREATE TABLE `actor` (
 `id` int(11) NOT NULL,
 `name` varchar(45) DEFAULT NULL,
 `update_time` datetime DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `actor` (`id`, `name`, `update_time`) VALUES (1,'a','2017-12-22 15:27:18');
INSERT INTO `actor` (`id`, `name`, `update_time`) VALUES (2,'b','2017-12-22 15:27:18');
INSERT INTO `actor` (`id`, `name`, `update_time`) VALUES (3,'c','2017-12-22 15:27:18');

DROP TABLE IF EXISTS `film`;
CREATE TABLE `film` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(10) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `film` (`id`, `name`) VALUES (3,'film0');
INSERT INTO `film` (`id`, `name`) VALUES (1,'film1');
INSERT INTO `film` (`id`, `name`) VALUES (2,'film2');

DROP TABLE IF EXISTS `film_actor`;
CREATE TABLE `film_actor` (
 `id` int(11) NOT NULL,
 `film_id` int(11) NOT NULL,
 `actor_id` int(11) NOT NULL,
 `remark` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `idx_film_actor_id` (`film_id`,`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `film_actor` (`id`, `film_id`, `actor_id`) VALUES (1,1,1);
INSERT INTO `film_actor` (`id`, `film_id`, `actor_id`) VALUES (2,1,2);
INSERT INTO `film_actor` (`id`, `film_id`, `actor_id`) VALUES (3,2,1);







