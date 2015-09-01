-- MySQL Workbench Synchronization
-- Generated: 2015-08-23 16:39
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: mays

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `product_price` 
ADD COLUMN `archived` VARCHAR(45) NULL DEFAULT 'N' COMMENT '标记存档\nY：存档\nN：正常（默认）' AFTER `remark`,
ADD INDEX `idx_price_code` (`price_code` ASC);

CREATE TABLE IF NOT EXISTS `product_picture` (
  `id` INT(20) NOT NULL AUTO_INCREMENT,
  `product_id` INT(20) NOT NULL DEFAULT 0 COMMENT '产品ID',
  `full_path` VARCHAR(250) NOT NULL DEFAULT '' COMMENT '上传后的完整路径',
  `original` VARCHAR(250) NOT NULL DEFAULT '' COMMENT '原始图片名',
  `rename` VARCHAR(250) NOT NULL DEFAULT '' COMMENT '重命名文件名',
  `is_cover` VARCHAR(45) NOT NULL DEFAULT 'N' COMMENT '标记封面/主图\nN：普通（默认）\nY：封面',
  `deleted` VARCHAR(45) NOT NULL DEFAULT 'N' COMMENT '标记删除\nN：未删除（默认）\nY：已删除',
  `gmt_created` DATETIME NULL DEFAULT NULL,
  `gmt_modified` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_product_id` (`product_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '产品图片信息';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
