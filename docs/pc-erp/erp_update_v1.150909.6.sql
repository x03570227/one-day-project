-- MySQL Workbench Synchronization
-- Generated: 2015-09-09 21:48
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: mays

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `pc-erp`.`product_price` 
CHANGE COLUMN `price_code` `price_code` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '价格类型，用于提供给不同层面的用户\nCOST(成本价)\nSHOP(批发价)\nRETAIL(零售价)' ,
CHANGE COLUMN `price_unit_code` `price_unit_code` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '单位\nRMB_YUAN(默认)\nRMB_FEN' ,
CHANGE COLUMN `price_value` `price_value` DECIMAL(12,2) NULL DEFAULT NULL COMMENT '价格值，最多保留2位小数' ,
CHANGE COLUMN `remark` `remark` VARCHAR(250) NULL DEFAULT '' COMMENT '备注\n成本价\n批发价\n限时促销价\n...' ;

ALTER TABLE `pc-erp`.`product_picture` 
CHANGE COLUMN `full_path` `full_path` VARCHAR(250) NOT NULL DEFAULT '' ,
ADD COLUMN `tags` VARCHAR(250) NULL DEFAULT '' COMMENT '可为图片打标签' AFTER `gmt_modified`;

CREATE TABLE IF NOT EXISTS `pc-erp`.`product_group` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL DEFAULT '',
  `remark` VARCHAR(250) NULL DEFAULT '',
  `gmt_created` DATETIME NULL DEFAULT NULL,
  `gmt_modified` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '产品分组，用于相关联产品归类';

CREATE TABLE IF NOT EXISTS `pc-erp`.`product_group_item` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `product_group_id` INT(20) NOT NULL DEFAULT 0,
  `product_id` INT(20) NOT NULL DEFAULT 0,
  `gmt_created` DATETIME NULL DEFAULT NULL,
  `gmt_modified` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_product_group_id` (`product_group_id` ASC),
  INDEX `idx_product_id` (`product_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '详细分组信息';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
