SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE  TABLE IF NOT EXISTS `pc-erp`.`sys_app` (
  `id` INT(20) NOT NULL AUTO_INCREMENT ,
  `cid` INT(20) NOT NULL DEFAULT 0 ,
  `domain` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '第三方系统的域名' ,
  `app_key` VARCHAR(45) NULL DEFAULT '' COMMENT 'app key' ,
  `app_secret` VARCHAR(45) NULL DEFAULT '' COMMENT 'app secret' ,
  `refresh_token` VARCHAR(45) NULL DEFAULT '' COMMENT '刷新令牌' ,
  `access_token` VARCHAR(45) NULL DEFAULT '' COMMENT '授权令牌' ,
  `gmt_created` DATETIME NULL DEFAULT NULL ,
  `gmt_modified` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '第三方服务商连接相关的信息';

CREATE  TABLE IF NOT EXISTS `pc-erp`.`trade` (
  `id` INT(20) NOT NULL AUTO_INCREMENT ,
  `cid` INT(20) NOT NULL ,
  `pid_first` INT(20) NOT NULL ,
  `trade_num` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '交易编号' ,
  `source_domain` VARCHAR(45) NOT NULL DEFAULT '' ,
  `source_type` VARCHAR(45) NULL DEFAULT NULL ,
  `gmt_created` DATETIME NULL DEFAULT NULL ,
  `gmt_modified` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_cid` (`cid` ASC) ,
  INDEX `idx_pid_first` (`pid_first` ASC) ,
  INDEX `idx_trade_num` (`trade_num` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '交易信息';

CREATE  TABLE IF NOT EXISTS `pc-erp`.`trade_define` (
  `id` INT(20) NOT NULL AUTO_INCREMENT ,
  `trade_id` INT(20) NOT NULL ,
  `details` LONGTEXT NULL DEFAULT NULL ,
  `gmt_created` DATETIME NULL DEFAULT NULL ,
  `gmt_modified` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_trade_id` (`trade_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '交易相关关的详细信息，一般用于存放第三方信息';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
