DROP TABLE IF EXISTS `email_content`;

CREATE TABLE `email_content` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `body` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_type` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
