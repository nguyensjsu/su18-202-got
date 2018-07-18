
-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'users'
--
-- ---

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `name` VARCHAR(300) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `facebook_id` VARCHAR(100) NULL DEFAULT NULL,
  `password_hash` VARCHAR NULL DEFAULT NULL,
  `token` VARCHAR NULL DEFAULT NULL,
  `token_expire_at` INTEGER NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'push_tokens'
--
-- ---

DROP TABLE IF EXISTS `push_tokens`;

CREATE TABLE `push_tokens` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `user_id` INTEGER NULL DEFAULT NULL,
  `device_token` VARCHAR NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Table 'rewards'
--
-- ---

DROP TABLE IF EXISTS `rewards`;

CREATE TABLE `rewards` (
  `id` INTEGER NULL AUTO_INCREMENT DEFAULT NULL,
  `user_id` VARCHAR NULL DEFAULT NULL,
  `balance` INTEGER NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ---
-- Foreign Keys
-- ---

ALTER TABLE `push_tokens` ADD FOREIGN KEY (id) REFERENCES `users` (`id`);
ALTER TABLE `rewards` ADD FOREIGN KEY (user_id) REFERENCES `users` (`id`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `users` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `push_tokens` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `rewards` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `users` (`id`,`name`,`email`,`facebook_id`,`password_hash`,`token`,`token_expire_at`) VALUES
-- ('','','','','','','');
-- INSERT INTO `push_tokens` (`id`,`user_id`,`device_token`) VALUES
-- ('','','');
-- INSERT INTO `rewards` (`id`,`user_id`,`balance`) VALUES
-- ('','','');
