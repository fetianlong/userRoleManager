/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.254
Source Server Version : 50624
Source Host           : 192.168.1.254:3306
Source Database       : yuediweb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-09 20:19:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `action_management`
-- ----------------------------
DROP TABLE IF EXISTS `action_management`;
CREATE TABLE `action_management` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`actionTitle`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`contacts`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`actionType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`state`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`location`  varchar(100) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`accomplishResults`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isClose`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sellerId`  bigint(20) NULL DEFAULT NULL ,
`executorId`  bigint(20) NULL DEFAULT NULL ,
`executorName`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`clientName`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`beginDateTime`  date NULL DEFAULT NULL ,
`endDateTime`  date NULL DEFAULT NULL ,
`spec`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`businessOpportunityId`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=24

;

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`Category`  int(11) NULL DEFAULT NULL COMMENT '0个人 1商家' ,
`ActivityName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名称' ,
`ActivityType`  int(11) NULL DEFAULT NULL COMMENT '0聚会 1讲座 2亲子游' ,
`Address`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动地址' ,
`Details`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动详情' ,
`Img`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ReviewStatus`  int(11) NULL DEFAULT 0 COMMENT '审核状态：0未审核 1审核通过 2审核失败' ,
`StartTime`  datetime NULL DEFAULT NULL COMMENT '活动开始时间' ,
`EndTime`  datetime NULL DEFAULT NULL COMMENT '活动结束时间' ,
`province`  int(11) NULL DEFAULT NULL ,
`city`  int(11) NULL DEFAULT NULL ,
`area`  int(11) NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Groupid`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '环信的群组ID' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=39

;

-- ----------------------------
-- Table structure for `activity_line`
-- ----------------------------
DROP TABLE IF EXISTS `activity_line`;
CREATE TABLE `activity_line` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`activityName`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`activityType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`activityAddr`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`activityPurpose`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`expectedTotalCost`  decimal(10,4) NULL DEFAULT NULL COMMENT '预计总费用' ,
`expectedCluesNumber`  bigint(10) NULL DEFAULT NULL COMMENT '预计线索数' ,
`sellerId`  bigint(20) NULL DEFAULT NULL ,
`excutedId`  bigint(20) NULL DEFAULT NULL COMMENT '执行人id' ,
`excutedName`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行人名称' ,
`beginDateTime`  timestamp NULL DEFAULT NULL ,
`endDateTime`  timestamp NULL DEFAULT NULL ,
`createDateTime`  timestamp NULL DEFAULT NULL ,
`createName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`updateDateTime`  timestamp NULL DEFAULT NULL ,
`updateName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`spec`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=13

;

-- ----------------------------
-- Table structure for `activity_user`
-- ----------------------------
DROP TABLE IF EXISTS `activity_user`;
CREATE TABLE `activity_user` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`ActivityId`  int(11) NULL DEFAULT NULL COMMENT '活动ID' ,
`UserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`XqStatus`  int(11) NULL DEFAULT 0 COMMENT '关注状态 0未关注 1已关注' ,
`BmStatus`  int(11) NULL DEFAULT 0 COMMENT '报名状态 0未报名 1已报名' ,
`Sex`  int(11) NULL DEFAULT NULL COMMENT '性别 1男 2女' ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=27

;

-- ----------------------------
-- Table structure for `as_areas`
-- ----------------------------
DROP TABLE IF EXISTS `as_areas`;
CREATE TABLE `as_areas` (
`id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
`parent_id`  int(11) UNSIGNED NOT NULL ,
`area_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`zipcode`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`name_py`  varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`level`  int(11) NULL DEFAULT NULL COMMENT '行政级别' ,
PRIMARY KEY (`id`)
)
ENGINE=MyISAM
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='地区信息'
AUTO_INCREMENT=3373

;

-- ----------------------------
-- Table structure for `attachments`
-- ----------------------------
DROP TABLE IF EXISTS `attachments`;
CREATE TABLE `attachments` (
`ID`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`userID`  bigint(20) NULL DEFAULT NULL ,
`AttName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AttDesc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AttType`  int(11) NULL DEFAULT NULL COMMENT '1-音乐、2-图片、3-文档、4-视频' ,
`upDT`  datetime NULL DEFAULT NULL ,
`AttSize`  decimal(10,0) NULL DEFAULT NULL ,
`Location`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='附件信息'

;

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='品牌'
AUTO_INCREMENT=5

;

-- ----------------------------
-- Table structure for `business_opportunity`
-- ----------------------------
DROP TABLE IF EXISTS `business_opportunity`;
CREATE TABLE `business_opportunity` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`businessOpportunityName`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商机名称' ,
`clientId`  bigint(20) NULL DEFAULT NULL COMMENT '客户名称' ,
`clientType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户类型' ,
`clueSource`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线索来源' ,
`clueType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '线索类型' ,
`expectedAmount`  decimal(10,4) NULL DEFAULT NULL ,
`sellerId`  bigint(20) NULL DEFAULT NULL ,
`createDateTime`  date NULL DEFAULT NULL ,
`createName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`updateDateTime`  date NULL DEFAULT NULL ,
`updateName`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`spec`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=37

;

-- ----------------------------
-- Table structure for `card_crawler`
-- ----------------------------
DROP TABLE IF EXISTS `card_crawler`;
CREATE TABLE `card_crawler` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`seed`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '种子url' ,
`url_path`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`title`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`content`  varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`areas`  bigint(20) NULL DEFAULT NULL ,
`circle`  bigint(20) NULL DEFAULT NULL ,
`regex`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL正则约束' ,
`mate_regex`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`crawlPath`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爬虫的crawlPath，crawlPath是维护URL信息的文件夹的路径，如果爬虫需要断点爬取，每次请选择相同的crawlPath' ,
`start_depth`  bigint(20) NULL DEFAULT NULL COMMENT '如果希望尽可能地爬取，这里可以设置一个很大的数，爬虫会在没有待爬取URL时自动停止' ,
`top_n`  bigint(20) NULL DEFAULT NULL COMMENT '设置每层爬取爬取的最大URL数量' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `cardfollow`
-- ----------------------------
DROP TABLE IF EXISTS `cardfollow`;
CREATE TABLE `cardfollow` (
`CFID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '跟帖ID' ,
`CFOrder`  bigint(20) NULL DEFAULT NULL ,
`CFTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CFDesc`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '（TEXT，富文本，段落）' ,
`CID`  bigint(20) NULL DEFAULT NULL ,
`AuditerID`  bigint(20) NULL DEFAULT NULL ,
`AuditDateTime2`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`AuditTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AuditStatus`  bigint(20) NULL DEFAULT NULL ,
`Status`  bigint(20) NULL DEFAULT NULL COMMENT '1-关闭，0-打开' ,
`CNum`  bigint(20) NULL DEFAULT NULL ,
`FFID`  bigint(20) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`UpdateDateTime3`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`Province`  int(11) NULL DEFAULT NULL ,
`City`  int(11) NULL DEFAULT NULL ,
`District`  int(11) NULL DEFAULT NULL ,
`Floor`  int(11) NOT NULL DEFAULT 0 COMMENT '楼层' ,
`CFType`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' ,
`IsCream`  bigint(20) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`CFID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='T8-帖子子表\r\nF-回帖ID\r\nF-主帖子ID\r\nF-回帖序号\r\nF-针对回帖'
AUTO_INCREMENT=14853

;

-- ----------------------------
-- Table structure for `cardfollow_copy`
-- ----------------------------
DROP TABLE IF EXISTS `cardfollow_copy`;
CREATE TABLE `cardfollow_copy` (
`CFID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '跟帖ID' ,
`CFOrder`  bigint(20) NULL DEFAULT NULL ,
`CFTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CFDesc`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '（TEXT，富文本，段落）' ,
`CID`  bigint(20) NULL DEFAULT NULL ,
`AuditerID`  bigint(20) NULL DEFAULT NULL ,
`AuditDateTime2`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`AuditTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AuditStatus`  bigint(20) NULL DEFAULT NULL ,
`Status`  bigint(20) NULL DEFAULT NULL COMMENT '1-关闭，0-打开' ,
`CNum`  bigint(20) NULL DEFAULT NULL ,
`FFID`  bigint(20) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`UpdateDateTime3`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`Province`  int(11) NULL DEFAULT NULL ,
`City`  int(11) NULL DEFAULT NULL ,
`District`  int(11) NULL DEFAULT NULL ,
`Floor`  int(11) NOT NULL DEFAULT 0 COMMENT '楼层' ,
`CFType`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' ,
`IsCream`  bigint(20) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`CFID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='T8-帖子子表\r\nF-回帖ID\r\nF-主帖子ID\r\nF-回帖序号\r\nF-针对回帖'
AUTO_INCREMENT=2030

;

-- ----------------------------
-- Table structure for `cardmain`
-- ----------------------------
DROP TABLE IF EXISTS `cardmain`;
CREATE TABLE `cardmain` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '帖子ID' ,
`CardType`  bigint(20) NULL DEFAULT NULL COMMENT 'F-帖子大类：胎教、亲子等' ,
`CardClass`  bigint(20) NULL DEFAULT NULL ,
`CardTitle`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AttID`  bigint(20) NULL DEFAULT NULL COMMENT 'F-帖子主题图片' ,
`CardDesc`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '（TEXT，富文本，段落）' ,
`KnowID`  bigint(20) NULL DEFAULT NULL ,
`AuditerId`  bigint(20) NULL DEFAULT NULL ,
`AuditDateTime2`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`AuditTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AuditStatus`  bigint(20) NULL DEFAULT NULL ,
`HiDesc`  bigint(20) NULL DEFAULT NULL COMMENT '1-关闭，0-打开' ,
`ANum`  bigint(20) NULL DEFAULT NULL ,
`CNum`  bigint(20) NULL DEFAULT NULL ,
`BNum`  bigint(20) NULL DEFAULT NULL ,
`IsTop`  tinyint(1) NULL DEFAULT NULL ,
`Level`  bigint(20) NULL DEFAULT NULL ,
`Remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`UpdateDateTime3`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`Province`  int(11) NULL DEFAULT NULL ,
`City`  int(11) NULL DEFAULT NULL ,
`District`  int(11) NULL DEFAULT NULL ,
`IsCream`  int(11) NULL DEFAULT 0 COMMENT '是否精华' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='T7-帖子主表\r\nF-帖子ID\r\nF-帖子大类\r\nF-帖子子类\r\nF-帖子主题图'
AUTO_INCREMENT=5885

;

-- ----------------------------
-- Table structure for `cardmain_copy`
-- ----------------------------
DROP TABLE IF EXISTS `cardmain_copy`;
CREATE TABLE `cardmain_copy` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '帖子ID' ,
`CardType`  bigint(20) NULL DEFAULT NULL COMMENT 'F-帖子大类：胎教、亲子等' ,
`CardClass`  bigint(20) NULL DEFAULT NULL ,
`CardTitle`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AttID`  bigint(20) NULL DEFAULT NULL COMMENT 'F-帖子主题图片' ,
`CardDesc`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '（TEXT，富文本，段落）' ,
`KnowID`  bigint(20) NULL DEFAULT NULL ,
`AuditerId`  bigint(20) NULL DEFAULT NULL ,
`AuditDateTime2`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`AuditTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`AuditStatus`  bigint(20) NULL DEFAULT NULL ,
`HiDesc`  bigint(20) NULL DEFAULT NULL COMMENT '1-关闭，0-打开' ,
`ANum`  bigint(20) NULL DEFAULT NULL ,
`CNum`  bigint(20) NULL DEFAULT NULL ,
`BNum`  bigint(20) NULL DEFAULT NULL ,
`IsTop`  tinyint(1) NULL DEFAULT NULL ,
`Level`  bigint(20) NULL DEFAULT NULL ,
`Remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`UpdateDateTime3`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`Province`  int(11) NULL DEFAULT NULL ,
`City`  int(11) NULL DEFAULT NULL ,
`District`  int(11) NULL DEFAULT NULL ,
`IsCream`  int(11) NULL DEFAULT 0 COMMENT '是否精华' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='T7-帖子主表\r\nF-帖子ID\r\nF-帖子大类\r\nF-帖子子类\r\nF-帖子主题图'
AUTO_INCREMENT=654

;

-- ----------------------------
-- Table structure for `circle_r_card`
-- ----------------------------
DROP TABLE IF EXISTS `circle_r_card`;
CREATE TABLE `circle_r_card` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CicleID`  bigint(20) NOT NULL ,
`CardID`  bigint(20) NOT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='T6-圈子帖子关系表\nF-圈子ID\nF-帖子ID'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `class_img`
-- ----------------------------
DROP TABLE IF EXISTS `class_img`;
CREATE TABLE `class_img` (
`ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID' ,
`Class_ID`  int(11) NULL DEFAULT NULL COMMENT '课程ID' ,
`Resource_ID`  int(11) NULL DEFAULT NULL COMMENT '资源ID' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=540

;

-- ----------------------------
-- Table structure for `class_info`
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`method`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法' ,
`role`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作用' ,
`task`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`step`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`VWeekID`  int(11) NULL DEFAULT NULL COMMENT '怀孕第几周' ,
`VDayID`  int(11) NULL DEFAULT NULL COMMENT '怀孕第几天' ,
`ctime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
`is_del`  tinyint(1) NULL DEFAULT 0 COMMENT '是否删除0 否；1是；' ,
`class_type_id`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=237

;

-- ----------------------------
-- Table structure for `class_interaction`
-- ----------------------------
DROP TABLE IF EXISTS `class_interaction`;
CREATE TABLE `class_interaction` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`Content`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文字或者语音目录' ,
`Class_ID`  int(11) NULL DEFAULT NULL COMMENT '课程id' ,
`User_ID`  int(11) NULL DEFAULT NULL COMMENT '用户ID' ,
`Img`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ctime`  datetime NULL DEFAULT NULL ,
`days`  int(5) NULL DEFAULT NULL ,
`sounds`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`soundTime`  varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ClassTaskId`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=219

;

-- ----------------------------
-- Table structure for `class_type`
-- ----------------------------
DROP TABLE IF EXISTS `class_type`;
CREATE TABLE `class_type` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`img`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`is_del`  tinyint(1) NULL DEFAULT 0 COMMENT '1 是；0否' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=22

;

-- ----------------------------
-- Table structure for `classinfo`
-- ----------------------------
DROP TABLE IF EXISTS `classinfo`;
CREATE TABLE `classinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Code`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Specification`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`HeadTeacher`  bigint(20) NOT NULL COMMENT '取自员工表' ,
`Category`  int(11) NULL DEFAULT NULL COMMENT '取自品牌' ,
`Franchisees_ID`  bigint(20) NOT NULL COMMENT '取自加盟商表，如果加盟商创建，默认所属加盟商为自己，不得修改' ,
`AssignLessonCount`  int(11) NULL DEFAULT 0 ,
`StudentLimit`  int(11) NULL DEFAULT NULL ,
`ClassRoom_ID`  bigint(20) NULL DEFAULT NULL COMMENT '取自教室表' ,
`State`  int(11) NULL DEFAULT NULL COMMENT '1-新建、2-已开班、3-已关闭' ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreateDateTime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`HeadTeacherName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CategoryText`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ClassRoomName`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='班级只有新建状态时可以修订基本信息和增加课时信息，已开班只能更换教室与老师，已关闭的班级不能再作任务修改。'
AUTO_INCREMENT=11

;

-- ----------------------------
-- Table structure for `classinfo_lesson`
-- ----------------------------
DROP TABLE IF EXISTS `classinfo_lesson`;
CREATE TABLE `classinfo_lesson` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ClassInfo_ID`  bigint(20) NOT NULL ,
`Lesson_ID`  bigint(20) NOT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-关系实体-班级课程;本班可以上的课程'
AUTO_INCREMENT=50

;

-- ----------------------------
-- Table structure for `classinfo_studentinfo`
-- ----------------------------
DROP TABLE IF EXISTS `classinfo_studentinfo`;
CREATE TABLE `classinfo_studentinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ClassInfo_ID`  bigint(20) NOT NULL ,
`StudentInfo_ID`  bigint(20) NOT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='V2.0-关系实体-班级学员'
AUTO_INCREMENT=13

;

-- ----------------------------
-- Table structure for `classlessons`
-- ----------------------------
DROP TABLE IF EXISTS `classlessons`;
CREATE TABLE `classlessons` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ClassInfo_ID`  bigint(20) NOT NULL ,
`ClassPeriod_ID`  bigint(20) NULL DEFAULT NULL ,
`DayOfWeek`  int(11) NULL DEFAULT NULL COMMENT '1-星期日、2-星期一、3-星期二、4-星期三、5-星期四、6-星期五、7-星期六，规则类型为周时有效' ,
`LessonDate`  date NOT NULL ,
`StartTime`  datetime NOT NULL COMMENT '时间：时、分' ,
`EndTime`  datetime NOT NULL COMMENT '时间：时、分' ,
`Lesson_ID`  bigint(20) NULL DEFAULT NULL ,
`Specification`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Teacher`  bigint(20) NULL DEFAULT NULL COMMENT '取自员工表' ,
`ClassRoom_ID`  bigint(20) NULL DEFAULT NULL COMMENT '取自教室表' ,
`State`  int(11) NOT NULL DEFAULT 1 COMMENT '1-未上课，2-已上课' ,
`Summary`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-班级课程实例'
AUTO_INCREMENT=59

;

-- ----------------------------
-- Table structure for `classlessontask`
-- ----------------------------
DROP TABLE IF EXISTS `classlessontask`;
CREATE TABLE `classlessontask` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ClassLessons_ID`  bigint(20) NOT NULL ,
`Specification`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-班级课程实例作业'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `classperiod`
-- ----------------------------
DROP TABLE IF EXISTS `classperiod`;
CREATE TABLE `classperiod` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ClassInfo_ID`  bigint(20) NOT NULL ,
`ClassPeriodRule_ID`  bigint(20) NULL DEFAULT NULL ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DayOfMonth`  int(11) NULL DEFAULT NULL COMMENT '当月的第几天，规则类型为月时有效' ,
`WeekOfMonth`  int(11) NULL DEFAULT NULL COMMENT '当月的第几个星期，规则类型为月时有效' ,
`DayOfWeek`  int(11) NULL DEFAULT NULL COMMENT '1-星期日、2-星期一、3-星期二、4-星期三、5-星期四、6-星期五、7-星期六，规则类型为周时有效' ,
`TimeInterval`  int(11) NOT NULL COMMENT '上课时段表，上午＼下午＼晚上等' ,
`OrdIndex`  int(11) NOT NULL COMMENT '班级生成课程实例时按顺序号滚动生成' ,
`Specification`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`StartTime`  datetime NOT NULL COMMENT '时间：时、分' ,
`EndTime`  datetime NOT NULL COMMENT '时间：时、分' ,
`MarketPrice`  decimal(18,4) NULL DEFAULT NULL ,
`PreferentialPrice`  decimal(18,4) NULL DEFAULT NULL ,
`CurrencyType`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'CNY' COMMENT 'CNY人民币、USD美元、EUR欧元、HKD港币、GBP英镑、JPY日元、KRW韩元、CAD加元、AUD澳元、CHF瑞郎、SGD新加坡元、MYR马来西亚币、IDR印尼、NZD新西兰、VND越南、THB泰铢、PHP菲律宾' ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='可按日、按周、按月循环'
AUTO_INCREMENT=29

;

-- ----------------------------
-- Table structure for `classperiodrule`
-- ----------------------------
DROP TABLE IF EXISTS `classperiodrule`;
CREATE TABLE `classperiodrule` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ClassInfo_ID`  bigint(20) NOT NULL ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Specification`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Category`  int(11) NOT NULL COMMENT '1-日、2-周、3-月' ,
`Lesson_ID`  bigint(20) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-班级课时规则;第个班同时只能有一条有效的规则'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `classroom`
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`NAME`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Locations`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Franchisees_ID`  bigint(20) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-教室'
AUTO_INCREMENT=15

;

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
`id`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`app`  decimal(3,0) NULL DEFAULT NULL ,
`appver`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`system`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`version`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`userid`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isuse`  decimal(2,0) NULL DEFAULT 1 ,
`createtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`updatetime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `clip_relation`
-- ----------------------------
DROP TABLE IF EXISTS `clip_relation`;
CREATE TABLE `clip_relation` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`cType`  int(11) NULL DEFAULT NULL COMMENT '1：图片，2：录音，3：胎教音乐' ,
`RelationID`  bigint(20) NULL DEFAULT NULL COMMENT '关联ID' ,
`UserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Week`  int(10) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V1.4-剪辑关联表'
AUTO_INCREMENT=964

;

-- ----------------------------
-- Table structure for `clip_url`
-- ----------------------------
DROP TABLE IF EXISTS `clip_url`;
CREATE TABLE `clip_url` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`UserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ClipUrl`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ClipPrefix`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Week`  int(10) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V1.4-剪辑URL表'
AUTO_INCREMENT=315

;

-- ----------------------------
-- Table structure for `codedomain`
-- ----------------------------
DROP TABLE IF EXISTS `codedomain`;
CREATE TABLE `codedomain` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Category`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ParentCategory`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CodeValue`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码值' ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='代码域'
AUTO_INCREMENT=67

;

-- ----------------------------
-- Table structure for `codesequency`
-- ----------------------------
DROP TABLE IF EXISTS `codesequency`;
CREATE TABLE `codesequency` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Category`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Prefix`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Sequency`  bigint(20) NOT NULL DEFAULT 0 ,
`Increment`  bigint(20) NOT NULL DEFAULT 1 ,
`Remark`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='代码顺序号'
AUTO_INCREMENT=89

;

-- ----------------------------
-- Table structure for `contact_person`
-- ----------------------------
DROP TABLE IF EXISTS `contact_person`;
CREATE TABLE `contact_person` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`full_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名' ,
`son_seller_id`  bigint(20) NOT NULL ,
`seller_id`  bigint(20) NOT NULL COMMENT '所属加盟商或商家' ,
`seller_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`birthday`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期' ,
`sex`  bigint(20) NULL DEFAULT NULL COMMENT '性别：0女，1男' ,
`key_person`  bigint(20) NULL DEFAULT 0 COMMENT '0：关键人，1：非关键人' ,
`branch`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门' ,
`position`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务' ,
`telephone`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系个电话' ,
`instant_message`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '即时通讯' ,
`email`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`personal_website`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人网站' ,
`postal_code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编' ,
`address`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`hobby`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爱好' ,
`remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`create_user`  bigint(20) NULL DEFAULT NULL ,
`create_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_time`  datetime NULL DEFAULT NULL ,
`update_user`  bigint(20) NULL DEFAULT NULL ,
`update_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`update_time`  datetime NULL DEFAULT NULL ,
`businessType`  varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=10

;

-- ----------------------------
-- Table structure for `course_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `course_feedback`;
CREATE TABLE `course_feedback` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`parent_id`  bigint(20) NULL DEFAULT NULL ,
`CustomerInfo_ID`  bigint(20) NULL DEFAULT NULL ,
`user_id`  bigint(20) NOT NULL ,
`uer_real_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`StudentInfo_ID`  bigint(20) NULL DEFAULT NULL ,
`Lesson_ID`  bigint(20) NULL DEFAULT NULL ,
`course_ware_id`  bigint(20) NOT NULL ,
`course_ware_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`franchisees_id`  bigint(20) NOT NULL ,
`feedback_time`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ClassLessons_ID`  bigint(20) NULL DEFAULT NULL ,
`State`  int(11) NULL DEFAULT NULL COMMENT '1-提问、2-已回复' ,
`Specification`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`creater_id`  bigint(20) NULL DEFAULT NULL ,
`creater_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_date`  char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-课程反馈'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `customerinfo`
-- ----------------------------
DROP TABLE IF EXISTS `customerinfo`;
CREATE TABLE `customerinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Code`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Nickname`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Sex`  int(11) NULL DEFAULT NULL COMMENT '0-未知、1-男、2-女' ,
`MobilePhone`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Email`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BornDate`  datetime NULL DEFAULT NULL ,
`CardCode`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Source`  int(11) NULL DEFAULT NULL COMMENT '0-未知、1-WEB前台、2-IOSAPP、3-AndroidApp、4-亲子直营店、5-亲子加盟商、6-母婴店' ,
`IsStudent`  tinyint(1) NOT NULL DEFAULT 0 ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NOT NULL ,
`FranchiseesId`  bigint(20) NOT NULL ,
`qq`  int(20) NULL DEFAULT NULL COMMENT 'qq号' ,
`weChat`  int(20) NULL DEFAULT NULL COMMENT '微信号' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='悦迪可以看所有客户信息，各直营店、加盟商等都只能看其本身客户'
AUTO_INCREMENT=13

;

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`User_ID`  bigint(20) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`App`  int(1) NULL DEFAULT NULL ,
`AppVersion`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`System`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SystemVersion`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`UpdateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='TJ1.0设备'
AUTO_INCREMENT=9931

;

-- ----------------------------
-- Table structure for `docinfo`
-- ----------------------------
DROP TABLE IF EXISTS `docinfo`;
CREATE TABLE `docinfo` (
`ResoureceInfo_ID`  bigint(20) NOT NULL ,
`Content`  varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`ResoureceInfo_ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='文档信息'

;

-- ----------------------------
-- Table structure for `employees`
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Franchisees_ID`  bigint(20) NULL DEFAULT NULL ,
`Code`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Position`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Nickname`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Sex`  int(11) NULL DEFAULT NULL COMMENT '0-未知、1-男、2-女' ,
`BornDate`  datetime NULL DEFAULT NULL ,
`CardCode`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Email`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MobilePhone`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FixedPhone`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`QQAccount`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MicroChartAccount`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Contacts`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ContactsPhone`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Specification`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-员工信息'
AUTO_INCREMENT=3

;

-- ----------------------------
-- Table structure for `favorites`
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`UserID`  bigint(20) NULL DEFAULT NULL ,
`Modual`  int(11) NULL DEFAULT NULL COMMENT '10：胎教，20：圈子，30：知识' ,
`Category`  int(11) NULL DEFAULT NULL COMMENT '11：胎教作业，12：韵动和健康作业，13：日记' ,
`ObjectID`  bigint(20) NULL DEFAULT NULL ,
`ResID`  bigint(20) NULL DEFAULT NULL ,
`URL`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`Summary1`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Summary2`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='TJ1.0收藏'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `fm_birth_audio`
-- ----------------------------
DROP TABLE IF EXISTS `fm_birth_audio`;
CREATE TABLE `fm_birth_audio` (
`id`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`name`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ext`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`path`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`desc`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`birthtype`  decimal(3,0) NULL DEFAULT NULL ,
`biztype`  decimal(3,0) NULL DEFAULT NULL ,
`isuse`  decimal(2,0) NULL DEFAULT 1 ,
`ispay`  decimal(2,0) NULL DEFAULT 0 ,
`abtype`  decimal(2,0) NULL DEFAULT NULL ,
`creatorid`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`creatorname`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`createtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `fm_personal_res`
-- ----------------------------
DROP TABLE IF EXISTS `fm_personal_res`;
CREATE TABLE `fm_personal_res` (
`id`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`userid`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`name`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ext`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`path`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`desc`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`birthtype`  decimal(3,0) NULL DEFAULT NULL ,
`biztype`  decimal(3,0) NULL DEFAULT NULL ,
`isuse`  decimal(2,0) NULL DEFAULT 1 ,
`restype`  decimal(2,0) NULL DEFAULT 1 ,
`creatorid`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`createtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `forumcircle`
-- ----------------------------
DROP TABLE IF EXISTS `forumcircle`;
CREATE TABLE `forumcircle` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '圈子ID' ,
`CircleType`  bigint(20) NOT NULL ,
`ObjectID`  bigint(20) NULL DEFAULT NULL ,
`KnowTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`province_id`  bigint(20) NULL DEFAULT NULL ,
`city_id`  bigint(20) NULL DEFAULT NULL ,
`county_id`  bigint(20) NULL DEFAULT NULL ,
`hospital_id`  bigint(20) NULL DEFAULT NULL ,
`year_months`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`index_top`  bigint(20) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Img`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='T5-圈子表\r\nF-圈子ID\r\nF-圈子大类ID（字典：1-医院、2-同月、3-同城）\r\n'
AUTO_INCREMENT=779

;

-- ----------------------------
-- Table structure for `franchisees`
-- ----------------------------
DROP TABLE IF EXISTS `franchisees`;
CREATE TABLE `franchisees` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Remark`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`ParentID`  bigint(20) NULL DEFAULT NULL ,
`OrgCode`  varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Category`  int(11) NULL DEFAULT NULL ,
`OfficeAddr`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`RegAddr`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BusinessScope`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Authentication`  int(11) NULL DEFAULT NULL ,
`ObligationOrg`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CharterCode`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CharterEffectiveDate`  datetime NULL DEFAULT NULL ,
`CharterExpiredDate`  datetime NULL DEFAULT NULL ,
`Province`  bigint(20) NULL DEFAULT NULL ,
`City`  bigint(20) NULL DEFAULT NULL ,
`Area`  bigint(20) NULL DEFAULT NULL ,
`longitude`  float NULL DEFAULT NULL ,
`latitude`  float NULL DEFAULT NULL ,
`Tel`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdateUser`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='加盟商'
AUTO_INCREMENT=23

;

-- ----------------------------
-- Table structure for `franchisees_lesson`
-- ----------------------------
DROP TABLE IF EXISTS `franchisees_lesson`;
CREATE TABLE `franchisees_lesson` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Franchisees_ID`  bigint(20) NOT NULL ,
`Lesson_ID`  bigint(20) NOT NULL ,
`StartDate`  datetime NOT NULL ,
`EndDate`  datetime NOT NULL ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='加盟商购买的课程'
AUTO_INCREMENT=1027

;

-- ----------------------------
-- Table structure for `gestationinfo`
-- ----------------------------
DROP TABLE IF EXISTS `gestationinfo`;
CREATE TABLE `gestationinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`StudentInfo_ID`  bigint(20) NOT NULL ,
`LastMensesDate`  datetime NULL DEFAULT NULL ,
`MensesDays`  int(11) NULL DEFAULT NULL ,
`ExpectedDate`  datetime NULL DEFAULT NULL ,
`RellyBornDate`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '含时间' ,
`BodySize`  int(11) NULL DEFAULT NULL COMMENT '单、双、三、四、五' ,
`BornProvince`  bigint(20) NULL DEFAULT NULL ,
`BornCity`  bigint(20) NULL DEFAULT NULL ,
`BornHospital`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医院表' ,
`ParturitionMode`  int(11) NULL DEFAULT NULL COMMENT '顺生、刨宫产' ,
`OtherSpecification`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-学员孕育信息'
AUTO_INCREMENT=14

;

-- ----------------------------
-- Table structure for `hospital_management`
-- ----------------------------
DROP TABLE IF EXISTS `hospital_management`;
CREATE TABLE `hospital_management` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`hospital_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`nature`  bigint(20) NULL DEFAULT NULL ,
`hospital_type`  bigint(20) NULL DEFAULT NULL ,
`hospital_code`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ccale`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`province_id`  bigint(20) NOT NULL ,
`province`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`city_id`  bigint(20) NOT NULL ,
`city`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`county_id`  bigint(20) NULL DEFAULT NULL ,
`county`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`address`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`telephone`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`introduce`  varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='医院信息'
AUTO_INCREMENT=400

;

-- ----------------------------
-- Table structure for `hx_user`
-- ----------------------------
DROP TABLE IF EXISTS `hx_user`;
CREATE TABLE `hx_user` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`UserId`  bigint(15) NULL DEFAULT NULL ,
`HxUserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`HxPwd`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Token`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=76

;

-- ----------------------------
-- Table structure for `ip_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `ip_login_log`;
CREATE TABLE `ip_login_log` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`user_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`seller_id`  bigint(20) NOT NULL ,
`seller_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`login_time`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`logout_time`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`login_ip`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`login_area`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`login_source`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=13

;

-- ----------------------------
-- Table structure for `knowledge`
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '知识ID' ,
`KLType`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '知识类型（胎教、亲子）【可以多选】\r\n            知识类型（胎教、亲子）【可以多选】\r\n            知识类型（胎教、亲子）【可以多选】\r\n            ' ,
`AttID`  bigint(20) NULL DEFAULT NULL COMMENT '附件ID，可以为空' ,
`KnowTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`KnowSub`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`KnowDesc`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '（TEXT，富文本，段落）' ,
`Remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=128

;

-- ----------------------------
-- Table structure for `knowledgeattachment`
-- ----------------------------
DROP TABLE IF EXISTS `knowledgeattachment`;
CREATE TABLE `knowledgeattachment` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`KnowledgeID`  bigint(20) NULL DEFAULT NULL ,
`AttlD`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`sort`  bigint(20) NULL DEFAULT NULL ,
`CreateUser`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`UpdateUser`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdateTime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='知识表附件'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `knowledgeremind`
-- ----------------------------
DROP TABLE IF EXISTS `knowledgeremind`;
CREATE TABLE `knowledgeremind` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Day`  bigint(20) NOT NULL COMMENT '1 - 280天' ,
`KnowTitle`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`KnowSub`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`KnowDesc`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '（TEXT，富文本，段落）' ,
`Remark`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreateName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ,
`UpdateName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdateTime`  timestamp NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V3.0-关爱提醒表'
AUTO_INCREMENT=247

;

-- ----------------------------
-- Table structure for `knowledgeresource`
-- ----------------------------
DROP TABLE IF EXISTS `knowledgeresource`;
CREATE TABLE `knowledgeresource` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`Knowledge_ID`  bigint(20) NULL DEFAULT NULL ,
`ResourceInfo_ID`  bigint(20) NULL DEFAULT NULL ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreateName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`UpdateName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdateDateTime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='知识资源关系表'
AUTO_INCREMENT=173

;

-- ----------------------------
-- Table structure for `lesson`
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Brand`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '孕期、亲子课、家庭课' ,
`Series`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1-韵动系列、2-艺术与身心灵、3-孕期家庭成长系列、4-营养与体重管理、5-音乐镇痛分娩套餐、6-产后修复与新生儿护理' ,
`Category`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1-公开课、2-收费课' ,
`State`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1-草稿、2-已发布、3-已失效' ,
`Goals`  varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Guide`  varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Tags`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
`SmallCoverUrl`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BigCoverUrl`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='课程信息'
AUTO_INCREMENT=99

;

-- ----------------------------
-- Table structure for `lessonsection`
-- ----------------------------
DROP TABLE IF EXISTS `lessonsection`;
CREATE TABLE `lessonsection` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Guide`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Teaching`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Enlightenment`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Category`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Tags`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
`Brand_ID`  bigint(20) NOT NULL COMMENT '孕期、亲子课、家庭课' ,
`Series_ID`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='课程小节'
AUTO_INCREMENT=216

;

-- ----------------------------
-- Table structure for `lessonsection_resource`
-- ----------------------------
DROP TABLE IF EXISTS `lessonsection_resource`;
CREATE TABLE `lessonsection_resource` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`LessonSection_ID`  bigint(20) NOT NULL ,
`ResourceInfo_ID`  bigint(20) NOT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='课程小节相关的资源'
AUTO_INCREMENT=684

;

-- ----------------------------
-- Table structure for `lessonsectiones`
-- ----------------------------
DROP TABLE IF EXISTS `lessonsectiones`;
CREATE TABLE `lessonsectiones` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Lesson_ID`  bigint(20) NOT NULL ,
`LessonSection_ID`  bigint(20) NOT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='组成课程的小节信息'
AUTO_INCREMENT=297

;

-- ----------------------------
-- Table structure for `memberinfo`
-- ----------------------------
DROP TABLE IF EXISTS `memberinfo`;
CREATE TABLE `memberinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Code`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Nickname`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Sex`  int(11) NULL DEFAULT NULL COMMENT '1-男、0-女' ,
`MobilePhone`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Email`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BornDate`  datetime NULL DEFAULT NULL ,
`CardCode`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Source`  int(11) NULL DEFAULT NULL ,
`IsStudent`  tinyint(1) NULL DEFAULT 0 ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`EndDateTime`  datetime NULL DEFAULT NULL ,
`franchiseesId`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0会员信息'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ParentId`  bigint(20) NULL DEFAULT NULL ,
`Url`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OrdIndex`  int(11) NULL DEFAULT 0 ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`menuType`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Issued`  bigint(20) NULL DEFAULT 0 COMMENT '是否允许给加盟商使用此菜单0:允许，1：不允许' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='菜单'
AUTO_INCREMENT=133

;

-- ----------------------------
-- Table structure for `operatelog`
-- ----------------------------
DROP TABLE IF EXISTS `operatelog`;
CREATE TABLE `operatelog` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`LogContent`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='操作日志'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `people_nearby`
-- ----------------------------
DROP TABLE IF EXISTS `people_nearby`;
CREATE TABLE `people_nearby` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`userName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`province`  int(11) NULL DEFAULT NULL ,
`city`  int(11) NULL DEFAULT NULL ,
`area`  int(11) NULL DEFAULT NULL ,
`longitude`  double(15,10) NULL DEFAULT 0.0000000000 COMMENT '经度' ,
`latitude`  double(15,10) NULL DEFAULT 0.0000000000 COMMENT '纬度' ,
`Sex`  int(11) NULL DEFAULT 2 COMMENT '1男2女' ,
`helpinfo`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '求助信息' ,
`sign`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名' ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`UpdateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=52

;

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
`id`  int(8) NOT NULL AUTO_INCREMENT COMMENT '自增主键' ,
`name`  char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限名' ,
`value`  char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限值' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='权限列表'
AUTO_INCREMENT=5

;

-- ----------------------------
-- Table structure for `postattachment`
-- ----------------------------
DROP TABLE IF EXISTS `postattachment`;
CREATE TABLE `postattachment` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`PostID`  bigint(20) NULL DEFAULT NULL COMMENT '反馈列表的主键ID' ,
`PosterID`  bigint(20) NULL DEFAULT NULL COMMENT '可为空' ,
`AttID`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
`sort`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='帖子附件'
AUTO_INCREMENT=286

;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
`ProductID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ProductName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SellerID`  bigint(20) NULL DEFAULT NULL ,
`ProductDesc`  varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Price`  decimal(10,0) NULL DEFAULT NULL ,
`Image`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Stock`  int(11) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreateUser`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录名' ,
`CreateTime`  datetime NULL DEFAULT NULL ,
`UpdateUser`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录名' ,
`UpdateTime`  datetime NULL DEFAULT NULL ,
`PriceOld`  decimal(10,0) NULL DEFAULT NULL ,
`Sellersign`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ProductID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='记录产品的详细信息'
AUTO_INCREMENT=206

;

-- ----------------------------
-- Table structure for `remindresource`
-- ----------------------------
DROP TABLE IF EXISTS `remindresource`;
CREATE TABLE `remindresource` (
`ID`  int(11) NOT NULL AUTO_INCREMENT ,
`KnowledgeRemind_ID`  bigint(20) NULL DEFAULT NULL ,
`ResourceInfo_ID`  bigint(20) NULL DEFAULT NULL ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreateName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`UpdateName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdateDateTime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='关爱提醒资源关系表'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `replyattachment`
-- ----------------------------
DROP TABLE IF EXISTS `replyattachment`;
CREATE TABLE `replyattachment` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ReplyID`  bigint(20) NULL DEFAULT NULL COMMENT '反馈列表的主键ID' ,
`ReplyUserID`  bigint(20) NULL DEFAULT NULL COMMENT '可为空' ,
`AttID`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
`sort`  bigint(20) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='帖子附件'
AUTO_INCREMENT=206

;

-- ----------------------------
-- Table structure for `resourceinfo`
-- ----------------------------
DROP TABLE IF EXISTS `resourceinfo`;
CREATE TABLE `resourceinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ResourceCode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`DisplayName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`ResourceName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Brand_ID`  bigint(20) NULL DEFAULT NULL ,
`Series_ID`  bigint(20) NULL DEFAULT NULL ,
`ResourceType`  int(11) NOT NULL COMMENT '1-音乐、2-图片、3-文档、4-视频' ,
`ExpandedName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不带点的扩展名' ,
`ResourceSize`  bigint(20) NULL DEFAULT NULL COMMENT '字节长度' ,
`ResourceURL`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源在服务器的相对应用根的URL地址' ,
`Tags`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Remark`  varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UseScene`  varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`lyrics`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`),
INDEX `Index_ResourceInfo_ResourceType` (`ResourceType`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='管理音乐、图片、文档、视频资源的信息'
AUTO_INCREMENT=5017

;

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Remark`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`roleType`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色'
AUTO_INCREMENT=42

;

-- ----------------------------
-- Table structure for `role_brand`
-- ----------------------------
DROP TABLE IF EXISTS `role_brand`;
CREATE TABLE `role_brand` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Role_ID`  bigint(20) NOT NULL ,
`Brand_ID`  bigint(20) NOT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色品牌关系'
AUTO_INCREMENT=43

;

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Role_ID`  bigint(20) NOT NULL ,
`Menu_ID`  bigint(20) NOT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色菜单关系'
AUTO_INCREMENT=905

;

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
`role_id`  bigint(20) NOT NULL ,
`permission_id`  bigint(20) NOT NULL ,
PRIMARY KEY (`role_id`, `permission_id`),
FOREIGN KEY (`role_id`) REFERENCES `role` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `role_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `role_userinfo`;
CREATE TABLE `role_userinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`UserInfo_ID`  bigint(20) NOT NULL ,
`Role_ID`  bigint(20) NOT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色用户关系'
AUTO_INCREMENT=119

;

-- ----------------------------
-- Table structure for `saler`
-- ----------------------------
DROP TABLE IF EXISTS `saler`;
CREATE TABLE `saler` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`SellerID`  bigint(20) NULL DEFAULT NULL ,
`UseID`  bigint(20) NULL DEFAULT NULL ,
`Name`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`Tel`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`SalerCode`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`TDCodeURL`  varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
`Sex`  int(11) NULL DEFAULT NULL ,
`CardCode`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`Email`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`QQ`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`WeiChat`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`Contacts`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`ContactsPhone`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`Spec`  varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`TwoCodeUrl`  varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`login_name`  varchar(32) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci
AUTO_INCREMENT=21

;

-- ----------------------------
-- Table structure for `saler_declare`
-- ----------------------------
DROP TABLE IF EXISTS `saler_declare`;
CREATE TABLE `saler_declare` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`SellerID`  bigint(20) NULL DEFAULT NULL ,
`declareName`  varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`declareTel`  bigint(20) NULL DEFAULT NULL ,
`declareNumber`  int(3) NULL DEFAULT NULL ,
`state`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1:审核，2:通过' ,
`spec`  varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=48

;

-- ----------------------------
-- Table structure for `saler_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `saler_userinfo`;
CREATE TABLE `saler_userinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`userId`  bigint(20) NULL DEFAULT NULL ,
`sallerId`  bigint(20) NULL DEFAULT NULL ,
`createDateTime`  timestamp NULL DEFAULT NULL ,
`createrName`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`updateDateTime`  timestamp NULL DEFAULT NULL ,
`upadateName`  varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isUsed`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=42

;

-- ----------------------------
-- Table structure for `saler_usero2c`
-- ----------------------------
DROP TABLE IF EXISTS `saler_usero2c`;
CREATE TABLE `saler_usero2c` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`saler_ID`  bigint(20) NOT NULL ,
`seller_ID`  bigint(20) NOT NULL ,
`user_ID`  bigint(20) NOT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='业务员商家-用户关系'
AUTO_INCREMENT=4

;

-- ----------------------------
-- Table structure for `seller`
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ParentID`  bigint(20) NULL DEFAULT NULL ,
`login_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`OrgCode`  varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Name`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Remark`  varchar(6000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Category`  int(11) NULL DEFAULT NULL ,
`BusinessScope`  varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Tel`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Province`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`City`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Area`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`OfficeAddr`  varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Authentication`  int(11) NULL DEFAULT NULL ,
`ObligationOrg`  varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`RegAddr`  varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CharterCode`  varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CharterEffectiveDate`  datetime NULL DEFAULT NULL ,
`CharterExpiredDate`  datetime NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`SellerSign`  int(1) NOT NULL DEFAULT 0 COMMENT '商家标识' ,
`longitude`  double(15,7) NULL DEFAULT 0.0000000 COMMENT '经度' ,
`latitude`  double(15,7) NULL DEFAULT 0.0000000 COMMENT '纬度' ,
`SelledCnt`  int(11) NULL DEFAULT NULL ,
`Level`  int(11) NULL DEFAULT NULL ,
`Phone`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`img`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题图片' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1679

;

-- ----------------------------
-- Table structure for `sellertdcode`
-- ----------------------------
DROP TABLE IF EXISTS `sellertdcode`;
CREATE TABLE `sellertdcode` (
`ID`  bigint(20) NOT NULL ,
`SellerID`  bigint(20) NULL DEFAULT NULL ,
`Saler`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`TDCodeURL`  varchar(128) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=gbk COLLATE=gbk_chinese_ci
COMMENT='*V2.1-业务员信息'

;

-- ----------------------------
-- Table structure for `series`
-- ----------------------------
DROP TABLE IF EXISTS `series`;
CREATE TABLE `series` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
`Brand_ID`  bigint(20) NOT NULL ,
`Category`  int(11) NOT NULL COMMENT '1-课程、2-小节' ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='系列'
AUTO_INCREMENT=101

;

-- ----------------------------
-- Table structure for `sta_birth_time`
-- ----------------------------
DROP TABLE IF EXISTS `sta_birth_time`;
CREATE TABLE `sta_birth_time` (
`id`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`userid`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`birthkey`  decimal(31,0) NULL DEFAULT NULL ,
`start`  decimal(31,0) NULL DEFAULT NULL ,
`end`  decimal(31,0) NULL DEFAULT NULL ,
`createtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`summary1`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary2`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary3`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary4`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary5`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isuse`  decimal(2,0) NULL DEFAULT 1 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `sta_pain_time`
-- ----------------------------
DROP TABLE IF EXISTS `sta_pain_time`;
CREATE TABLE `sta_pain_time` (
`id`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`userid`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`birthkey`  decimal(31,0) NULL DEFAULT NULL ,
`ispain`  decimal(2,0) NULL DEFAULT NULL ,
`timepoint`  decimal(31,0) NULL DEFAULT NULL ,
`createtime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`summary1`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary2`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary3`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary4`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`summary5`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isuse`  decimal(2,0) NULL DEFAULT 1 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `store`
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`SType`  int(11) NULL DEFAULT NULL COMMENT '1：胎教，2：知识，3：帖子，4：楼层，5：商家，6：商品' ,
`RelationID`  bigint(20) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ,
`week`  int(10) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V3.0-收藏表'
AUTO_INCREMENT=122

;

-- ----------------------------
-- Table structure for `studentinfo`
-- ----------------------------
DROP TABLE IF EXISTS `studentinfo`;
CREATE TABLE `studentinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CustomerInfo_ID`  bigint(20) NOT NULL ,
`CustomerInfo_Code`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`StudentNo`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Franchisees_ID`  bigint(20) NULL DEFAULT NULL ,
`EntranceDate`  datetime NULL DEFAULT NULL ,
`Email`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MobilePhone`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FixedPhone`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`QQAccount`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MicroChartAccount`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Province`  bigint(20) NULL DEFAULT NULL ,
`City`  bigint(20) NULL DEFAULT NULL ,
`District`  bigint(20) NULL DEFAULT NULL ,
`SpecificationAddress`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Contacts`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ContactsPhone`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Specification`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Saler`  bigint(20) NULL DEFAULT NULL COMMENT '取自员工表，推荐人、招生顾问' ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-学员信息'
AUTO_INCREMENT=14

;

-- ----------------------------
-- Table structure for `studentlearnrecord`
-- ----------------------------
DROP TABLE IF EXISTS `studentlearnrecord`;
CREATE TABLE `studentlearnrecord` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`CustomerInfo_ID`  bigint(20) NOT NULL ,
`StudentInfo_ID`  bigint(20) NOT NULL ,
`ClassInfo_ID`  bigint(20) NOT NULL ,
`ClassPeriod_ID`  bigint(20) NULL DEFAULT NULL ,
`Lesson_ID`  bigint(20) NULL DEFAULT NULL ,
`ClassLessons_ID`  bigint(20) NOT NULL ,
`Teacher`  bigint(20) NULL DEFAULT NULL COMMENT '取自员工表' ,
`ClassRoom_ID`  bigint(20) NULL DEFAULT NULL COMMENT '取自教室表' ,
`Bill_SerialNumber`  varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MarketPrice`  decimal(18,4) NULL DEFAULT NULL ,
`PreferentialPrice`  decimal(18,4) NULL DEFAULT NULL ,
`CurrencyType`  varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'CNY' COMMENT 'CNY人民币、USD美元、EUR欧元、HKD港币、GBP英镑、JPY日元、KRW韩元、CAD加元、AUD澳元、CHF瑞郎、SGD新加坡元、MYR马来西亚币、IDR印尼、NZD新西兰、VND越南、THB泰铢、PHP菲律宾' ,
`LessonDate`  date NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V2.0-学员上课记录'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `tags`
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Category`  int(11) NOT NULL COMMENT '1-默认、2-课程品牌、3-课程系列、4-资源分类、5-课程小节分类' ,
`Tags`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='标签'
AUTO_INCREMENT=3

;

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
`RelationID`  bigint(20) NULL DEFAULT NULL ,
`TaskDescription`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TaskType`  int(11) NULL DEFAULT NULL COMMENT '1-课程作业、2-课程小节作业' ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`Brand`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '孕期、亲子课、家庭课' ,
`Series`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1-韵动系列、2-艺术与身心灵、3-孕期家庭成长系列、4-营养与体重管理、5-音乐镇痛分娩套餐、6-产后修复与新生儿护理、7-胎教' ,
`Category`  int(11) NOT NULL COMMENT '1-公开课、2-收费课' ,
`State`  int(11) NULL DEFAULT NULL COMMENT '1-草稿、2-已发布、3-已失效' ,
`Img`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`WorkType`  int(11) NULL DEFAULT 1 COMMENT '1-韵动和健康,2-胎教' ,
`title`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`TaskAsk`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`VWeekID`  int(11) NULL DEFAULT 0 ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='作业'
AUTO_INCREMENT=174

;

-- ----------------------------
-- Table structure for `taskbackinfo`
-- ----------------------------
DROP TABLE IF EXISTS `taskbackinfo`;
CREATE TABLE `taskbackinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '反馈ID' ,
`ActorID`  bigint(20) NULL DEFAULT NULL ,
`LessonInsID`  bigint(20) NULL DEFAULT NULL COMMENT '非学员可以为空' ,
`TaslkID`  bigint(20) NULL DEFAULT NULL ,
`Message`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ActDT`  datetime NULL DEFAULT NULL ,
`ActorType`  int(11) NULL DEFAULT NULL COMMENT '1=注册用户     2=学员     9=教师' ,
`ActType`  int(11) NULL DEFAULT NULL COMMENT '文字、在线音频、图片、其他文件对象等' ,
`ToID`  bigint(20) NULL DEFAULT NULL COMMENT '针对哪个反馈ID进行回复' ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='在线学习课程作业反馈信息'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `taskbackobject`
-- ----------------------------
DROP TABLE IF EXISTS `taskbackobject`;
CREATE TABLE `taskbackobject` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`ActID`  bigint(20) NULL DEFAULT NULL COMMENT '反馈列表的主键ID' ,
`ActorID`  bigint(20) NULL DEFAULT NULL COMMENT '可为空' ,
`LessonInsID`  bigint(20) NULL DEFAULT NULL COMMENT '非学员可以为空' ,
`TaskID`  bigint(20) NULL DEFAULT NULL COMMENT '可为空' ,
`AttID`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='在线学习课程作业反馈附件'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `tasklessons`
-- ----------------------------
DROP TABLE IF EXISTS `tasklessons`;
CREATE TABLE `tasklessons` (
`ID`  bigint(20) NOT NULL ,
`LessonID`  bigint(20) NULL DEFAULT NULL ,
`VWeekID`  bigint(20) NULL DEFAULT NULL ,
`TaskID`  bigint(20) NULL DEFAULT NULL ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='课程、孕周与在线作业关系，即在线作业按课程、孕周二维进行配置'

;

-- ----------------------------
-- Table structure for `taskresource`
-- ----------------------------
DROP TABLE IF EXISTS `taskresource`;
CREATE TABLE `taskresource` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`Task_ID`  bigint(20) NOT NULL ,
`ResourceInfo_ID`  bigint(20) NOT NULL ,
`OrdIndex`  int(11) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='作业资源'
AUTO_INCREMENT=300

;

-- ----------------------------
-- Table structure for `taskvweek`
-- ----------------------------
DROP TABLE IF EXISTS `taskvweek`;
CREATE TABLE `taskvweek` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`VWeekID`  bigint(20) NULL DEFAULT NULL ,
`TaskID`  bigint(20) NULL DEFAULT NULL ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='孕周与在线作业关系，即在线作业按孕周进行配置'
AUTO_INCREMENT=12

;

-- ----------------------------
-- Table structure for `track_record`
-- ----------------------------
DROP TABLE IF EXISTS `track_record`;
CREATE TABLE `track_record` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`parent_id`  bigint(20) NOT NULL ,
`content`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`track_time`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_date`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_id`  bigint(20) NULL DEFAULT NULL ,
`create_name`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='客户/会员跟踪记录'
AUTO_INCREMENT=4

;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`lgnname`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pwd`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`username`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`mobilenumber`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sex`  decimal(12,0) NULL DEFAULT NULL ,
`country`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`state`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`city`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`locality`  varchar(765) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`isuse`  decimal(2,0) NULL DEFAULT 1 ,
`sysfrom`  decimal(3,0) NULL DEFAULT 1 ,
`type`  decimal(3,0) NULL DEFAULT 2 ,
`pregnancydate`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`birthday`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`createtime`  timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`pms`  decimal(2,0) NULL DEFAULT 1 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Table structure for `user_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `user_feedback`;
CREATE TABLE `user_feedback` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`UserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Feedback`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=10

;

-- ----------------------------
-- Table structure for `user_relationship`
-- ----------------------------
DROP TABLE IF EXISTS `user_relationship`;
CREATE TABLE `user_relationship` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`ZuserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主用户' ,
`FuserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '辅用户' ,
`Status`  int(11) NULL DEFAULT 0 COMMENT '0请求好友状态 1相互之间是好友 2删除好友状态' ,
`reason`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '好友请求理由' ,
`CreateDateTime`  datetime NULL DEFAULT NULL COMMENT '创建时间' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=68

;

-- ----------------------------
-- Table structure for `usercircle`
-- ----------------------------
DROP TABLE IF EXISTS `usercircle`;
CREATE TABLE `usercircle` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`UserID`  int(11) NULL DEFAULT NULL ,
`UserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CircleID`  int(11) NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT 0 ,
`CreaterID`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreaterDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`IndexTop`  int(11) NULL DEFAULT 0 ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='V3.0-用户圈子关系表'
AUTO_INCREMENT=34785

;

-- ----------------------------
-- Table structure for `userdiary`
-- ----------------------------
DROP TABLE IF EXISTS `userdiary`;
CREATE TABLE `userdiary` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '反馈ID' ,
`UserID`  bigint(20) NULL DEFAULT NULL ,
`LessonInsID`  bigint(20) NULL DEFAULT NULL COMMENT '非学员可以为空' ,
`TaslkID`  bigint(20) NULL DEFAULT NULL COMMENT '可以为空' ,
`Message`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ActDT`  datetime NULL DEFAULT NULL ,
`ActType`  int(11) NULL DEFAULT NULL COMMENT '文字、在线音频、图片、其他文件对象等' ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='日记信息'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `userdiaryobject`
-- ----------------------------
DROP TABLE IF EXISTS `userdiaryobject`;
CREATE TABLE `userdiaryobject` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`DiaryID`  bigint(20) NULL DEFAULT NULL COMMENT '反馈列表的主键ID' ,
`AttID`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户日记上传的相关附件'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`UserName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`Email`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`State`  int(11) NOT NULL COMMENT '1-正常、2-冻结' ,
`Pwd`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`RoleCategory`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Franchisees_ID`  bigint(20) NULL DEFAULT NULL ,
`UserType`  int(11) NOT NULL DEFAULT 0 COMMENT '1-系统用户，0-普通用户' ,
`salt`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`maces`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录用户MAC地址，若是机器有多个网卡使用英文分号（;）将多个网卡地址分开' ,
`RealName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Birthdate`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`IDNumber`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sex`  int(11) NULL DEFAULT NULL COMMENT '1:男，0:女' ,
`telephone`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`remark`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户信息'
AUTO_INCREMENT=753

;

-- ----------------------------
-- Table structure for `userinfo2c`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo2c`;
CREATE TABLE `userinfo2c` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`UserName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`FullName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Email`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`State`  int(11) NULL DEFAULT 1 COMMENT '1-正常、2-冻结' ,
`Pwd`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Salt`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserType`  int(11) NOT NULL DEFAULT 0 COMMENT '1-注册用户，2-学员' ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
`Birthday`  datetime NULL DEFAULT NULL ,
`CustomerInfo_ID`  bigint(20) NULL DEFAULT NULL ,
`Student_No`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Tel`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`img`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`NickName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称' ,
`Province`  int(11) NULL DEFAULT NULL COMMENT '省' ,
`City`  int(11) NULL DEFAULT NULL COMMENT '市' ,
`Area`  int(11) NULL DEFAULT NULL COMMENT '区' ,
`Address`  varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址' ,
`Sex`  int(11) NULL DEFAULT 2 COMMENT '性别' ,
`HospID`  int(11) NULL DEFAULT NULL COMMENT '所属医院ID' ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='TJ1.0用户信息2C'
AUTO_INCREMENT=1604

;

-- ----------------------------
-- Table structure for `userseller`
-- ----------------------------
DROP TABLE IF EXISTS `userseller`;
CREATE TABLE `userseller` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`SellerID`  bigint(20) NULL DEFAULT NULL COMMENT '商家ID' ,
`UserID`  bigint(20) NULL DEFAULT NULL ,
`RType`  int(11) NULL DEFAULT NULL ,
`TDCodeID`  bigint(20) NULL DEFAULT NULL COMMENT '二维码id' ,
`OrdIndex`  int(11) NULL DEFAULT NULL ,
`CreateDateTime`  datetime NULL DEFAULT NULL ,
INDEX `ID` (`ID`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=67

;

-- ----------------------------
-- Table structure for `usertaskrecord`
-- ----------------------------
DROP TABLE IF EXISTS `usertaskrecord`;
CREATE TABLE `usertaskrecord` (
`ID`  bigint(20) NOT NULL AUTO_INCREMENT ,
`UserID`  bigint(20) NOT NULL ,
`VWeekID`  bigint(20) NULL DEFAULT NULL ,
`LessonDatetime`  datetime NULL DEFAULT NULL ,
`IsDeleteFlag`  tinyint(1) NOT NULL DEFAULT 0 ,
`CreaterId`  bigint(20) NULL DEFAULT NULL ,
`CreaterName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDateTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`ID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='*V2.0-用户查看作业记录'
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `validate_tel`
-- ----------------------------
DROP TABLE IF EXISTS `validate_tel`;
CREATE TABLE `validate_tel` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`Tel`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Code`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UpdateTime`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Auto increment value for `action_management`
-- ----------------------------
ALTER TABLE `action_management` AUTO_INCREMENT=24;

-- ----------------------------
-- Auto increment value for `activity`
-- ----------------------------
ALTER TABLE `activity` AUTO_INCREMENT=39;

-- ----------------------------
-- Auto increment value for `activity_line`
-- ----------------------------
ALTER TABLE `activity_line` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for `activity_user`
-- ----------------------------
ALTER TABLE `activity_user` AUTO_INCREMENT=27;

-- ----------------------------
-- Auto increment value for `as_areas`
-- ----------------------------
ALTER TABLE `as_areas` AUTO_INCREMENT=3373;

-- ----------------------------
-- Auto increment value for `brand`
-- ----------------------------
ALTER TABLE `brand` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for `business_opportunity`
-- ----------------------------
ALTER TABLE `business_opportunity` AUTO_INCREMENT=37;

-- ----------------------------
-- Auto increment value for `card_crawler`
-- ----------------------------
ALTER TABLE `card_crawler` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `cardfollow`
-- ----------------------------
ALTER TABLE `cardfollow` AUTO_INCREMENT=14853;

-- ----------------------------
-- Auto increment value for `cardfollow_copy`
-- ----------------------------
ALTER TABLE `cardfollow_copy` AUTO_INCREMENT=2030;

-- ----------------------------
-- Auto increment value for `cardmain`
-- ----------------------------
ALTER TABLE `cardmain` AUTO_INCREMENT=5885;

-- ----------------------------
-- Auto increment value for `cardmain_copy`
-- ----------------------------
ALTER TABLE `cardmain_copy` AUTO_INCREMENT=654;

-- ----------------------------
-- Auto increment value for `circle_r_card`
-- ----------------------------
ALTER TABLE `circle_r_card` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `class_img`
-- ----------------------------
ALTER TABLE `class_img` AUTO_INCREMENT=540;

-- ----------------------------
-- Auto increment value for `class_info`
-- ----------------------------
ALTER TABLE `class_info` AUTO_INCREMENT=237;

-- ----------------------------
-- Auto increment value for `class_interaction`
-- ----------------------------
ALTER TABLE `class_interaction` AUTO_INCREMENT=219;

-- ----------------------------
-- Auto increment value for `class_type`
-- ----------------------------
ALTER TABLE `class_type` AUTO_INCREMENT=22;

-- ----------------------------
-- Auto increment value for `classinfo`
-- ----------------------------
ALTER TABLE `classinfo` AUTO_INCREMENT=11;

-- ----------------------------
-- Auto increment value for `classinfo_lesson`
-- ----------------------------
ALTER TABLE `classinfo_lesson` AUTO_INCREMENT=50;

-- ----------------------------
-- Auto increment value for `classinfo_studentinfo`
-- ----------------------------
ALTER TABLE `classinfo_studentinfo` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for `classlessons`
-- ----------------------------
ALTER TABLE `classlessons` AUTO_INCREMENT=59;

-- ----------------------------
-- Auto increment value for `classlessontask`
-- ----------------------------
ALTER TABLE `classlessontask` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `classperiod`
-- ----------------------------
ALTER TABLE `classperiod` AUTO_INCREMENT=29;

-- ----------------------------
-- Auto increment value for `classperiodrule`
-- ----------------------------
ALTER TABLE `classperiodrule` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `classroom`
-- ----------------------------
ALTER TABLE `classroom` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for `clip_relation`
-- ----------------------------
ALTER TABLE `clip_relation` AUTO_INCREMENT=964;

-- ----------------------------
-- Auto increment value for `clip_url`
-- ----------------------------
ALTER TABLE `clip_url` AUTO_INCREMENT=315;

-- ----------------------------
-- Auto increment value for `codedomain`
-- ----------------------------
ALTER TABLE `codedomain` AUTO_INCREMENT=67;

-- ----------------------------
-- Auto increment value for `codesequency`
-- ----------------------------
ALTER TABLE `codesequency` AUTO_INCREMENT=89;

-- ----------------------------
-- Auto increment value for `contact_person`
-- ----------------------------
ALTER TABLE `contact_person` AUTO_INCREMENT=10;

-- ----------------------------
-- Auto increment value for `course_feedback`
-- ----------------------------
ALTER TABLE `course_feedback` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `customerinfo`
-- ----------------------------
ALTER TABLE `customerinfo` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for `device`
-- ----------------------------
ALTER TABLE `device` AUTO_INCREMENT=9931;

-- ----------------------------
-- Auto increment value for `employees`
-- ----------------------------
ALTER TABLE `employees` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `favorites`
-- ----------------------------
ALTER TABLE `favorites` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `forumcircle`
-- ----------------------------
ALTER TABLE `forumcircle` AUTO_INCREMENT=779;

-- ----------------------------
-- Auto increment value for `franchisees`
-- ----------------------------
ALTER TABLE `franchisees` AUTO_INCREMENT=23;

-- ----------------------------
-- Auto increment value for `franchisees_lesson`
-- ----------------------------
ALTER TABLE `franchisees_lesson` AUTO_INCREMENT=1027;

-- ----------------------------
-- Auto increment value for `gestationinfo`
-- ----------------------------
ALTER TABLE `gestationinfo` AUTO_INCREMENT=14;

-- ----------------------------
-- Auto increment value for `hospital_management`
-- ----------------------------
ALTER TABLE `hospital_management` AUTO_INCREMENT=400;

-- ----------------------------
-- Auto increment value for `hx_user`
-- ----------------------------
ALTER TABLE `hx_user` AUTO_INCREMENT=76;

-- ----------------------------
-- Auto increment value for `ip_login_log`
-- ----------------------------
ALTER TABLE `ip_login_log` AUTO_INCREMENT=13;

-- ----------------------------
-- Auto increment value for `knowledge`
-- ----------------------------
ALTER TABLE `knowledge` AUTO_INCREMENT=128;

-- ----------------------------
-- Auto increment value for `knowledgeattachment`
-- ----------------------------
ALTER TABLE `knowledgeattachment` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `knowledgeremind`
-- ----------------------------
ALTER TABLE `knowledgeremind` AUTO_INCREMENT=247;

-- ----------------------------
-- Auto increment value for `knowledgeresource`
-- ----------------------------
ALTER TABLE `knowledgeresource` AUTO_INCREMENT=173;

-- ----------------------------
-- Auto increment value for `lesson`
-- ----------------------------
ALTER TABLE `lesson` AUTO_INCREMENT=99;

-- ----------------------------
-- Auto increment value for `lessonsection`
-- ----------------------------
ALTER TABLE `lessonsection` AUTO_INCREMENT=216;

-- ----------------------------
-- Auto increment value for `lessonsection_resource`
-- ----------------------------
ALTER TABLE `lessonsection_resource` AUTO_INCREMENT=684;

-- ----------------------------
-- Auto increment value for `lessonsectiones`
-- ----------------------------
ALTER TABLE `lessonsectiones` AUTO_INCREMENT=297;

-- ----------------------------
-- Auto increment value for `memberinfo`
-- ----------------------------
ALTER TABLE `memberinfo` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `menu`
-- ----------------------------
ALTER TABLE `menu` AUTO_INCREMENT=133;

-- ----------------------------
-- Auto increment value for `operatelog`
-- ----------------------------
ALTER TABLE `operatelog` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `people_nearby`
-- ----------------------------
ALTER TABLE `people_nearby` AUTO_INCREMENT=52;

-- ----------------------------
-- Auto increment value for `permission`
-- ----------------------------
ALTER TABLE `permission` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for `postattachment`
-- ----------------------------
ALTER TABLE `postattachment` AUTO_INCREMENT=286;

-- ----------------------------
-- Auto increment value for `product`
-- ----------------------------
ALTER TABLE `product` AUTO_INCREMENT=206;

-- ----------------------------
-- Auto increment value for `remindresource`
-- ----------------------------
ALTER TABLE `remindresource` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `replyattachment`
-- ----------------------------
ALTER TABLE `replyattachment` AUTO_INCREMENT=206;

-- ----------------------------
-- Auto increment value for `resourceinfo`
-- ----------------------------
ALTER TABLE `resourceinfo` AUTO_INCREMENT=5017;

-- ----------------------------
-- Auto increment value for `role`
-- ----------------------------
ALTER TABLE `role` AUTO_INCREMENT=42;

-- ----------------------------
-- Auto increment value for `role_brand`
-- ----------------------------
ALTER TABLE `role_brand` AUTO_INCREMENT=43;

-- ----------------------------
-- Auto increment value for `role_menu`
-- ----------------------------
ALTER TABLE `role_menu` AUTO_INCREMENT=905;

-- ----------------------------
-- Auto increment value for `role_userinfo`
-- ----------------------------
ALTER TABLE `role_userinfo` AUTO_INCREMENT=119;

-- ----------------------------
-- Auto increment value for `saler`
-- ----------------------------
ALTER TABLE `saler` AUTO_INCREMENT=21;

-- ----------------------------
-- Auto increment value for `saler_declare`
-- ----------------------------
ALTER TABLE `saler_declare` AUTO_INCREMENT=48;

-- ----------------------------
-- Auto increment value for `saler_userinfo`
-- ----------------------------
ALTER TABLE `saler_userinfo` AUTO_INCREMENT=42;

-- ----------------------------
-- Auto increment value for `saler_usero2c`
-- ----------------------------
ALTER TABLE `saler_usero2c` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for `seller`
-- ----------------------------
ALTER TABLE `seller` AUTO_INCREMENT=1679;

-- ----------------------------
-- Auto increment value for `series`
-- ----------------------------
ALTER TABLE `series` AUTO_INCREMENT=101;

-- ----------------------------
-- Auto increment value for `store`
-- ----------------------------
ALTER TABLE `store` AUTO_INCREMENT=122;

-- ----------------------------
-- Auto increment value for `studentinfo`
-- ----------------------------
ALTER TABLE `studentinfo` AUTO_INCREMENT=14;

-- ----------------------------
-- Auto increment value for `studentlearnrecord`
-- ----------------------------
ALTER TABLE `studentlearnrecord` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `tags`
-- ----------------------------
ALTER TABLE `tags` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `task`
-- ----------------------------
ALTER TABLE `task` AUTO_INCREMENT=174;

-- ----------------------------
-- Auto increment value for `taskbackinfo`
-- ----------------------------
ALTER TABLE `taskbackinfo` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `taskbackobject`
-- ----------------------------
ALTER TABLE `taskbackobject` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `taskresource`
-- ----------------------------
ALTER TABLE `taskresource` AUTO_INCREMENT=300;

-- ----------------------------
-- Auto increment value for `taskvweek`
-- ----------------------------
ALTER TABLE `taskvweek` AUTO_INCREMENT=12;

-- ----------------------------
-- Auto increment value for `track_record`
-- ----------------------------
ALTER TABLE `track_record` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for `user_feedback`
-- ----------------------------
ALTER TABLE `user_feedback` AUTO_INCREMENT=10;

-- ----------------------------
-- Auto increment value for `user_relationship`
-- ----------------------------
ALTER TABLE `user_relationship` AUTO_INCREMENT=68;

-- ----------------------------
-- Auto increment value for `usercircle`
-- ----------------------------
ALTER TABLE `usercircle` AUTO_INCREMENT=34785;

-- ----------------------------
-- Auto increment value for `userdiary`
-- ----------------------------
ALTER TABLE `userdiary` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `userdiaryobject`
-- ----------------------------
ALTER TABLE `userdiaryobject` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `userinfo`
-- ----------------------------
ALTER TABLE `userinfo` AUTO_INCREMENT=753;

-- ----------------------------
-- Auto increment value for `userinfo2c`
-- ----------------------------
ALTER TABLE `userinfo2c` AUTO_INCREMENT=1604;

-- ----------------------------
-- Auto increment value for `userseller`
-- ----------------------------
ALTER TABLE `userseller` AUTO_INCREMENT=67;

-- ----------------------------
-- Auto increment value for `usertaskrecord`
-- ----------------------------
ALTER TABLE `usertaskrecord` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `validate_tel`
-- ----------------------------
ALTER TABLE `validate_tel` AUTO_INCREMENT=6;
