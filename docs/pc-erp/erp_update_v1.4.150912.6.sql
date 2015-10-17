-- MySQL Workbench Synchronization
-- Generated: 2015-09-12 21:10
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: mays

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `pc-erp`.`product_group` 
ADD COLUMN `cid` INT(20) NULL DEFAULT NULL AFTER `id`,
ADD COLUMN `uid_created` INT(20) NULL DEFAULT NULL AFTER `cid`,
ADD INDEX `idx_cid` (`cid` ASC);

ALTER TABLE `pc-erp`.`product_group_item` 
ADD COLUMN `is_primary` VARCHAR(45) NULL DEFAULT NULL COMMENT '是否为某系列主要产品\nN：不是（默认）\nY：是\n原则上，一个系列只能有一个主要产品' AFTER `gmt_modified`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
