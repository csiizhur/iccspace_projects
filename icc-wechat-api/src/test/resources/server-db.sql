
-- 2016.9.13 新增字段
alter table SHOPS add HISTORYID varchar(32);

-- 2016.9.14 新增用户表的最后访问时间字段
alter table USER add LASTACCESSTIME datetime;

-- 2016.9.18 新增消息回复表
DROP TABLE IF EXISTS `WEIXIN_TEXTMSG`;
CREATE TABLE `WEIXIN_TEXTMSG` (
  `ID` varchar(32) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL,
  `TEXT_CONTENT` text,
  `CREATE_USER` varchar(32) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `DELETED` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 2016.9.19 修改shops图片表的外键(删除更新CASCADE)
-- 删除外键，再新加外键
-- ALTER TABLE SHOPS_PHOTOS_INFO
-- ADD CONSTRAINT `shops_photos_info_ibfk_2` FOREIGN KEY (`SHOPSID`) REFERENCES `shops_history` (`BASE_SHOPSID`) ON DELETE CASCADE ON UPDATE CASCADE;

-- 2016.9.20 增加物业信息表   增加商铺历史表的所属物业ID字段  增加外键
drop table if exists PROPERTYS;

/*==============================================================*/
/* Table: PROPERTYS                                             */
/*==============================================================*/
create table PROPERTYS
(
   ID                   varchar(32) not null,
   PROPERTYNAME         varchar(32),
   PROPERTYTYPE         varchar(32),
   CREATE_TIME          datetime,
   DELETE_TIME          datetime,
   DELETE_USERID        varchar(32),
   DELETED              int(1),
   primary key (ID)
);

alter table SHOPS_HISTORY add PROPERTYID varchar(32);

alter table SHOPS_HISTORY add constraint FK_Reference_14 foreign key (PROPERTYID)
      references PROPERTYS (ID) on delete restrict on update restrict;
      
-- 2016.9.23 新增SHOPS_HISTORY表发布类型字段(出租or求租)
alter table SHOPS_HISTORY add RELEASE_TYPE int(1) NOT NULL default 0;
  -- 修改USER表OPENID字段长度
alter table USER modify column OPENID varchar(30);

-- 2016.9.27 修改USER表USER_ROLE字段默认值
alter table USER alter column USER_ROLE set default 0;

-- 2016.9.28 增加商铺表的搜索次数(点击)字段
alter table SHOPS_HISTORY add USER_CLICK int(11) default 0;

-- 2016.9.29 城市 区域 街道 数据(苏州)
-- ----------------------------
-- Records of CITY_INFO
-- ----------------------------

-- ----------------------------
-- Records of AREA_INFO
-- ----------------------------

-- ----------------------------
-- Records of STREET_INFO
-- ----------------------------


-- 2016.9.30 --新建日志记录表
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `T_ENTITY_ACCESS_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `T_ENTITY_ACCESS_LOG`;
CREATE TABLE `T_ENTITY_ACCESS_LOG` (
  `ID` varchar(32) NOT NULL  COMMENT '主键',
  `OPERATOR` varchar(32) DEFAULT NULL COMMENT '操作者',
  `SERVICE_NAME` varchar(80) DEFAULT NULL COMMENT '服务名称（DAO接口完整名）',
  `METHOD_NAME` varchar(32) DEFAULT NULL COMMENT '方法名称',
  `PARAMS_CONTENT` longtext COMMENT '参数内容',
  `OPERATION_TIME` datetime DEFAULT NULL COMMENT '请求时间,格式：yyyy-MM-dd HH:mm:ss',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体访问日志表';

-- 2016.10.9 新增SHOPS_HISTORY字段
alter table SHOPS_HISTORY add CURRENTSHOP_STATUS varchar(25) comment '当前店铺状态';
alter table SHOPS_HISTORY add EXPECT_RENTFEE decimal(10) comment '期望租金';
alter table SHOPS_HISTORY add RENT_TIME datetime comment '出租时间';
alter table SHOPS_HISTORY add RENT_BRAND varchar(255) comment '租户品牌';
alter table SHOPS_HISTORY add FLOOR int(11) comment '所处楼层';
alter table SHOPS_HISTORY add OPENROOM varchar(255) comment '开间';
alter table SHOPS_HISTORY add STORIES double comment '层高';
alter table SHOPS_HISTORY add COLUMNSBETWEEN varchar(255) comment '柱间';
alter table SHOPS_HISTORY add DEPTH double comment '进深';
alter table SHOPS_HISTORY add CURRENTSHOPBUSINESS_TYPE varchar(255) comment '当前店铺业态';
alter table SHOPS_HISTORY add SURPLUSLEASE_TREM varchar(255) comment '剩余租期';
alter table SHOPS_HISTORY add CONTRACT_ENDDATE datetime comment '原合同截至日';
alter table SHOPS_HISTORY add CONTRACT_RENTFEE decimal(10) comment '原合同租金';
alter table SHOPS_HISTORY add SHARERENTMODEL varchar(255) comment '合租方式';
alter table SHOPS_HISTORY add SPLITRENT_FEE decimal(10) comment '合租需分摊租金';
alter table SHOPS_HISTORY add MOBILEPHONE varchar(11) comment '手机号';

-- 2016.10.10 乐观锁 版本号字段
alter table SHOPS_HISTORY add VERSION int(4) NOT NULL default 1 comment '版本号'
 -- oss字段
alter table SHOPS_PHOTOS_INFO add DELETED int(1) default 0 comment '删除标志';
alter table SHOPS_PHOTOS_INFO add IMAGE_NAME varchar(64) comment '图片名称';
alter table SHOPS_PHOTOS_INFO add OSS_URL varchar(255) comment 'oss资源路径';
alter table SHOPS_PHOTOS_INFO add OSS_UPLOAD_FLG int(1) NOT NULL default 0 comment 'oss上传标志';
 -- SHOPS_HISTORY
alter table SHOPS_HISTORY add RENOVATION  varchar(255) comment '装修';
alter table SHOPS_HISTORY add PARKING_LOT varchar(255) comment '车位';
alter table SHOPS_HISTORY add TENANT_PRODUCT_REQUIRE text comment '对租户的产品要求';
alter table SHOPS_HISTORY add SUBLET_BRAND varchar(255) comment '你的品牌';
alter table SHOPS_HISTORY add SUBLET_MODE varchar(255) comment '转租方式';
alter table SHOPS_HISTORY add SUBLET_FEE decimal(10) comment '转租费';
alter table SHOPS_HISTORY add SUBLET_REASON varchar(255) comment '转租原因';

alter table SHOPS_HISTORY modify column SHOPS_NAME varchar(255);
alter table SHOPS_HISTORY add DELETED int(1) default 0 comment '0:未删除    1:已删除';
alter table SHOPS_HISTORY add DELETED_USERID varchar(32) comment '删除用户id';
-- 所有decimal字段都改为(10,2)
alter table SHOPS_HISTORY modify column RENT_FEE decimal(10,2);

-- 创建业态表
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `BUSINESSTYPE`
-- ----------------------------
DROP TABLE IF EXISTS `BUSINESSTYPE`;
CREATE TABLE `BUSINESSTYPE` (
  `ID` varchar(32) NOT NULL,
  `BUSINESSTYPE_CODE` varchar(64) DEFAULT NULL COMMENT '业态码',
  `BUSINESSTYPE_NAME` varchar(255) DEFAULT NULL COMMENT '业态名称',
  `CREATE_TIME` datetime DEFAULT NULL,
  `CREATE_USERID` varchar(32) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `UPDATE_USERID` varchar(32) DEFAULT NULL,
  `DELETED` int(1) DEFAULT '0' COMMENT '是否删除 0：未删除  1：已删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table USER modify column HEADIMG varchar(255);
alter table T_ENTITY_ACCESS_LOG modify column METHOD_NAME varchar(128);
alter table SHOPS_HISTORY add EXPECT_SHOPSIZE varchar(64) comment '期望面积';
alter table SHOPS_HISTORY add BUSINESS_TYPE varchar(64) comment '经营业态';

-- 求租商铺表
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `rent_shops`
-- ----------------------------
DROP TABLE IF EXISTS `RENT_SHOPS`;
CREATE TABLE `RENT_SHOPS` (
  `RENT_ID` varchar(32) NOT NULL DEFAULT '',
  `CITYNO` varchar(25) DEFAULT NULL,
  `AREANO` varchar(25) DEFAULT NULL,
  `STREETNO` varchar(25) DEFAULT NULL,
  `ADDRESS` text,
  `BUSINESSTYPE` varchar(64) DEFAULT NULL COMMENT '经营业态',
  `EXPECTSHOPSIZE` varchar(64) DEFAULT NULL COMMENT '期望面积',
  `EXPECTRENTFEE` decimal(10,2) DEFAULT NULL COMMENT '期望租金',
  `RENTTREM` varchar(255) DEFAULT NULL COMMENT '租赁时间(年)',
  `ESTATESTYPE` varchar(255) DEFAULT NULL COMMENT '物业类型',
  `STORIES` double(10,2) DEFAULT NULL COMMENT '层高',
  `RENOVATION` varchar(255) DEFAULT NULL COMMENT '装修',
  `PARKINGLOT` varchar(255) DEFAULT NULL COMMENT '车位',
  `SUBLETBRAND` varchar(255) DEFAULT NULL COMMENT '你的品牌',
  `SHARERENTMODEL` varchar(255) DEFAULT NULL COMMENT '合租方式',
  `SPLITRENTFEE` decimal(10,2) DEFAULT NULL COMMENT '合租需分摊租金',
  `USERID` varchar(32) DEFAULT NULL COMMENT '发布人',
  `MOBILEPHONE` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `COLLECTION_FREQUEN` int(11) DEFAULT NULL,
  `FORWARD_FREQUEN` int(11) DEFAULT NULL,
  `COMMENTS_FREQUEN` int(11) DEFAULT NULL,
  `THUMBNAIL_URL` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `USER_CLICK` int(11) DEFAULT 0 COMMENT '点击数',
  `VERSION` int(11) DEFAULT 1 COMMENT '版本号',
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DELETED` int(1) DEFAULT '0' COMMENT '0:未删除 1：已删除',
  `DELETED_USERID` varchar(32) DEFAULT NULL COMMENT '删除用户id',
  `RELEASE_TYPE` int(1) DEFAULT NULL COMMENT '发布类型 2：求租 ',
  PRIMARY KEY (`RENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table SHOPS add LNG decimal(10,7) comment '经度';
alter table SHOPS add LAT decimal(10,7) comment '纬度';

alter table SHOPS_HISTORY add COLLECTION_FREQUEN int(11) default 0 comment '收藏次数';
alter table SHOPS_HISTORY add FORWARD_FREQUEN int(11) default 0 comment '转发次数';
alter table SHOPS_HISTORY add COMMENTS_FREQUEN int(11) default 0 comment '评论次数';

alter table SHOPS_HISTORY add EXPECT_RENTFEE_MIN varchar(255) comment '租金最小值';
alter table SHOPS_HISTORY add EXPECT_RENTFEE_MAX varchar(255) comment '租金最大值';
alter table RENT_SHOPS add EXPECTRENTFEE_MIN varchar(255) comment '租金最小值';
alter table RENT_SHOPS add EXPECTRENTFEE_MAX varchar(255) comment '租金最大值';
alter table RENT_SHOPS add EXPECTSHOPSIZE_MIN varchar(255) comment '面积最小值';
alter table RENT_SHOPS add EXPECTSHOPSIZE_MAX varchar(255) comment '面积最大值';

alter table SHOPS modify column LAT varchar(64) comment '纬度';
alter table SHOPS modify column LNG varchar(64) comment '经度';
alter table SHOPS_HISTORY modify column FLOOR varchar(64) comment '所处楼层';

alter table SHOPS_HISTORY add UPDATE_TIME datetime comment '修改时间';
alter table SHOPS_COMMENTS add REPLY_COMMENTSID varchar(32) comment '评论回复ID';

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `USER_MESSAGE`
-- ----------------------------
DROP TABLE IF EXISTS `USER_MESSAGE`;
CREATE TABLE `USER_MESSAGE` (
  `MSG_ID` varchar(32) NOT NULL,
  `FROM_USERID` varchar(32) DEFAULT NULL,
  `TO_USERID` varchar(32) DEFAULT NULL,
  `MSG_CONTENT` text ,
  `CREATE_TIME` datetime DEFAULT NULL,
  `DELETED` int(1) DEFAULT 0,
  PRIMARY KEY (`MSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table USER_MESSAGE add IS_READ int(1) default 0 comment '是否已读 0:未读 1:已读';

alter table SHOPS_HISTORY add EQUIPMENTLIST text comment '设备清单(转租)';
alter table SHOPS_HISTORY add WEEKORMONTH int(1) comment '可供合租时间段(合租)1：按月2：按周';
alter table SHOPS_HISTORY add WEEKORMONTHDATA varchar(255) comment '按月 按周';
alter table SHOPS_HISTORY add RENTVAILABLESIZE varchar(255) comment '可供合租的面积';

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `RECOMMEND_PROPERTY`
-- ----------------------------
DROP TABLE IF EXISTS `RECOMMEND_PROPERTY`;
CREATE TABLE `RECOMMEND_PROPERTY` (
  `RECOMMEND_ID` varchar(32) NOT NULL comment '推荐房源ID',
  `RECOMMEND_USERID` varchar(32) DEFAULT NULL comment '推荐用户ID',
  `PROPERTY_ADDRESS` varchar(255) DEFAULT NULL comment '物业地址',
  `PROPERTY_SIZE` varchar(64) DEFAULT NULL comment '物业面积',
  `PROPERTY_USERNAME` varchar(64) comment '业主称呼',
  `PROPERTY_MOBILEPHONE` varchar(64) comment '业主电话',
  `CREATE_TIME` datetime DEFAULT NULL,
  `DELETED` int(1) DEFAULT 0 comment '0:未删除1:已删除',
  PRIMARY KEY (`RECOMMEND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `RECOMMEND_RENTUSER`
-- ----------------------------
DROP TABLE IF EXISTS `RECOMMEND_RENTUSER`;
CREATE TABLE `RECOMMEND_RENTUSER` (
  `RECOMMEND_ID` varchar(32) NOT NULL comment '推荐租户ID',
  `RECOMMEND_USERID` varchar(32) DEFAULT NULL comment '推荐用户ID',
  `RENT_ADDRESS` varchar(255) DEFAULT NULL comment '拟开店城市区域',
  `RENT_BRANDNAME` varchar(255) DEFAULT NULL comment '品牌名称',
  `RENT_SIZE` varchar(64) DEFAULT NULL comment '需求面积',
  `RENT_USERNAME` varchar(64) comment '租户称呼',
  `RENT_MOBILEPHONE` varchar(64) comment '租户电话',
  `CREATE_TIME` datetime DEFAULT NULL,
  `DELETED` int(1) DEFAULT 0 comment '0:未删除1:已删除',
  PRIMARY KEY (`RECOMMEND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `BUSINESS_COOPERATION`
-- ----------------------------
DROP TABLE IF EXISTS `BUSINESS_COOPERATION`;
CREATE TABLE `BUSINESS_COOPERATION` (
  `BUSINESS_ID` varchar(32) NOT NULL comment '商务合作ID',
  `COOPERATION_USERID` varchar(32) DEFAULT NULL comment '合作用户ID',
  `COOPERATION_MOBILEPHONE` varchar(64) DEFAULT NULL comment '合作用户电话',
  `COOPERATION_CONTENT` text comment '合作内容',
  `CREATE_TIME` datetime DEFAULT NULL,
  `DELETED` int(1) DEFAULT 0 comment '0:未删除1:已删除',
  PRIMARY KEY (`BUSINESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `USER_LEAVE_MESSAGE`
-- ----------------------------
DROP TABLE IF EXISTS `USER_LEAVE_MESSAGE`;
CREATE TABLE `USER_LEAVE_MESSAGE` (
  `MSG_ID` varchar(32) NOT NULL comment '留言ID',
  `FROM_USERID` varchar(32) DEFAULT NULL comment '留言用户ID',
  `MSG_CONTENT` text comment '留言内容',
  `CREATE_TIME` datetime DEFAULT NULL,
  `DELETED` int(1) DEFAULT 0 comment '0:未删除1:已删除',
  PRIMARY KEY (`MSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 删除外键67 图片和评论表


SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `USER_ACCESS_SHOPS`
-- ----------------------------
DROP TABLE IF EXISTS `USER_ACCESS_SHOPS`;
CREATE TABLE `USER_ACCESS_SHOPS` (
  `ACCESS_ID` varchar(32) NOT NULL DEFAULT '',
  `ACCESS_TIME` datetime DEFAULT NULL COMMENT '访问时间',
  `ACCESS_USERID` varchar(32) DEFAULT NULL COMMENT '访问用户',
  `ACCESS_COUNT` int(11) DEFAULT '0' COMMENT '访问次数 默认0',
  `SHOPSID` varchar(32) DEFAULT NULL COMMENT '商铺ID',
  `USERID` varchar(32) DEFAULT NULL COMMENT '该商铺的用户ID',
  `VERSION` int(11) DEFAULT '0' COMMENT '版本号',
  `STATUS` int(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ACCESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 删除委托表外键(商铺表shops_history的id)
ALTER TABLE ENTRUST_LEASE DROP FOREIGN KEY FK_Reference_13;