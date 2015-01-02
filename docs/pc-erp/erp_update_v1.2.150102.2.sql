SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

ALTER TABLE `trade` ADD COLUMN `status` INT(11) NULL DEFAULT 0 COMMENT '订单状态\n0：新订单（默认）\n正整数：未完成订单\n负整数：已完成订单'  AFTER `gmt_modified` , CHANGE COLUMN `source_domain` `source_domain` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '订单来源，域名'  , CHANGE COLUMN `source_type` `source_type` VARCHAR(45) NULL DEFAULT NULL COMMENT '来源类型\nAPI：通过API（默认）\nMANUAL：手动添加'  ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;