/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/9/7 16:53:39                            */
/*==============================================================*/


drop table if exists ADVERTISEMENT_APPLICATIONS;

drop table if exists AGENT_USERINFO;

drop table if exists AREA_INFO;

drop table if exists BLACKLIST;

drop table if exists CITY_INFO;

drop table if exists COMPLAINTS;

drop table if exists ENTRUST_LEASE;

drop table if exists ICCB_TRANSACTION;

drop table if exists SENSITIVE_WORDS;

drop table if exists SHOPS;

drop table if exists SHOPS_COMMENTS;

drop table if exists SHOPS_HISTORY;

drop table if exists SHOPS_PHOTOS_INFO;

drop table if exists STREET_INFO;

drop table if exists SYS_NOTICE;

drop table if exists TRANSACTION;

drop table if exists USER;

drop table if exists USER_COLLECTION;

drop table if exists USER_WALLET;

/*==============================================================*/
/* Table: ADVERTISEMENT_APPLICATIONS                            */
/*==============================================================*/
create table ADVERTISEMENT_APPLICATIONS
(
   ID                   varchar(32) not null,
   APPLY_USERID         varchar(32),
   APPLY_TYPE           int(1),
   APPLY_CONTENT        text,
   MOBILE               varchar(11),
   APPLY_STATUS         int(1),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: AGENT_USERINFO                                        */
/*==============================================================*/
create table AGENT_USERINFO
(
   USERID               varchar(32) not null,
   IDENTITY_ID          varchar(18),
   MOBILE               varchar(11),
   USERNAME             varchar(25),
   PHOTO                varchar(255),
   CREATE_DATE          date,
   UPDATE_DATE          date,
   CREATENO             varchar(32),
   UPDATENO             varchar(32),
   DELETED              int(1),
   primary key (USERID)
);

/*==============================================================*/
/* Table: AREA_INFO                                             */
/*==============================================================*/
create table AREA_INFO
(
   ID                   varchar(32) not null,
   AREANAME             varchar(25),
   AREANO               varchar(25),
   AREA_STATUS          int(1),
   BELONG_CITYNO        varchar(25),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: BLACKLIST                                             */
/*==============================================================*/
create table BLACKLIST
(
   ID                   varchar(32) not null,
   USERID               varchar(32),
   BLACK_USERID         varchar(32),
   BLACKLIST_STATUS     int(1),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: CITY_INFO                                             */
/*==============================================================*/
create table CITY_INFO
(
   ID                   varchar(32) not null,
   CITYNAME             varchar(25),
   CITYNO               varchar(25),
   CITY_STATUS          int(1),
   DELETED              int(2),
   primary key (ID)
);

/*==============================================================*/
/* Table: COMPLAINTS                                            */
/*==============================================================*/
create table COMPLAINTS
(
   ID                   varchar(32) not null,
   LINK_SHOPSID         varchar(32),
   COMPLAINT_USERID     varchar(32),
   COMPLAINT_TIME       datetime,
   COMPLAINT_TYPE       int(1),
   COMPLAINT_STATUS     int(1),
   COMPLAINT_RESULT     text,
   RESULT_TIME          datetime,
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: ENTRUST_LEASE                                         */
/*==============================================================*/
create table ENTRUST_LEASE
(
   ID                   varchar(32) not null,
   USERID               varchar(32),
   AGENT_USERID         varchar(32),
   ENTRUST_USERID       varchar(32),
   SHOPSID              varchar(32),
   AGENT_SCHEDULE       int(1),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: ICCB_TRANSACTION                                      */
/*==============================================================*/
create table ICCB_TRANSACTION
(
   ID                   varchar(32) not null,
   TRANS_NO             varchar(255),
   WALLETID             varchar(32),
   TRANS_TYPE           int(1),
   TRANS_TIME           datetime,
   TRANS_STATUS         int(1),
   TRANS_AMOUNT         int,
   DELETE_TIME          datetime,
   DELETE_USERID        varchar(32),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: SENSITIVE_WORDS                                       */
/*==============================================================*/
create table SENSITIVE_WORDS
(
   ID                   varchar(32) not null,
   WORDS                text,
   DELETE_TIME          datetime,
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: SHOPS                                                 */
/*==============================================================*/
create table SHOPS
(
   ID                   varchar(32) not null,
   SHOP_SIZE            double,
   CITY                 varchar(25),
   AREA                 varchar(25),
   STREET               varchar(25),
   ADDRESS              text,
   ESTATES_TYPE         varchar(255),
   COLLECTION_FREQUEN   int(11),
   FORWARD_FREQUEN      int(11),
   COMMENTS_FREQUEN     int(11),
   IS_SHOW              int(1),
   CREATE_TIME          datetime,
   UPDATE_TIME          datetime,
   DELETED              int(1),
   ADDRESS_UNIQUE       varchar(255),
   primary key (ID)
);

/*==============================================================*/
/* Table: SHOPS_COMMENTS                                        */
/*==============================================================*/
create table SHOPS_COMMENTS
(
   ID                   varchar(32) not null,
   SHOPSID              varchar(32),
   USERID               varchar(32),
   COMMENTS_TIME        datetime,
   COMMENTS_CONTENT     text,
   REPLY_USERID         varchar(32),
   DELETE_TIME          datetime,
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: SHOPS_HISTORY                                         */
/*==============================================================*/
create table SHOPS_HISTORY
(
   ID                   varchar(32) not null,
   BASE_SHOPSID         varchar(32),
   SHOPS_NAME           varchar(25),
   SHOPS_BRAND          varchar(25),
   RENT_TYPE            int(1),
   RENT_FEE             decimal,
   TOTAL_RENT_FEE       decimal,
   RENT_TREM            varchar(255),
   EXPECT_BUSINESS_TYPE varchar(255),
   ESTATE_FEE           decimal,
   ESTATES_TYPE         varchar(255),
   RELEASE_TIME         datetime,
   USERID               varchar(32),
   RELEASE_STATUS       int(1),
   THUMBNAIL_URL        varchar(255),
   CREATE_TIME          datetime,
   SHOPS_UNIQUE         varchar(255),
   primary key (ID)
);

/*==============================================================*/
/* Table: SHOPS_PHOTOS_INFO                                     */
/*==============================================================*/
create table SHOPS_PHOTOS_INFO
(
   ID                   varchar(32) not null,
   SHOPSID              varchar(32),
   IMAGE_URL1           varchar(255),
   IMAGE_URL2           varchar(255),
   IMAGE_URL3           varchar(255),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: STREET_INFO                                           */
/*==============================================================*/
create table STREET_INFO
(
   ID                   varchar(32) not null,
   STREETNAME           varchar(25),
   STREETNO             varchar(25),
   STREET_STATUS        int(1),
   BELONG_AREANO        varchar(25),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: SYS_NOTICE                                            */
/*==============================================================*/
create table SYS_NOTICE
(
   ID                   varchar(32) not null,
   NOTICE_NAME          varchar(25),
   NOTICE_TYPE          int(1),
   NOTICE_CONTENT       text,
   THUMBNAIL_URL        varchar(255),
   VALID_TIME           datetime,
   RELEASE_USERID       varchar(32),
   RELEASE_TIME         datetime,
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: TRANSACTION                                           */
/*==============================================================*/
create table TRANSACTION
(
   ID                   varchar(32) not null,
   TRANS_NO             varchar(255),
   USERID               varchar(32),
   TRANS_TYPE           int(1),
   CHANNEL              int(1),
   TRANS_TIME           datetime,
   TRANS_STATUS         int(1),
   TRANS_AMOUNT         decimal,
   DELETE_USERID        varchar(32),
   DELETE_TIME          datetime,
   DELETED              int(1),
   primary key (ID)
);

alter table TRANSACTION comment '指 用户的支付交易流水信息';

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   ID                   varchar(32) not null,
   OPENID               varchar(25),
   NICKNAME             varchar(25),
   HEADIMG              varchar(40),
   USERNAME             varchar(20),
   SEX                  int(1),
   AGE                  int(11),
   MOBILEPHONE          varchar(11),
   MANAGEHISTORY        text,
   SUBSCRIBE_TIME       datetime,
   IS_SUBSCRIBE         int(1),
   USER_ROLE            int(1),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: USER_COLLECTION                                       */
/*==============================================================*/
create table USER_COLLECTION
(
   ID                   varchar(32) not null,
   USERID               varchar(32),
   SHOPSID              varchar(32),
   CREATE_TIME          datetime,
   DELETE_TIME          datetime,
   DELETE_USERID        varchar(32),
   DELETED              int(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: USER_WALLET                                           */
/*==============================================================*/
create table USER_WALLET
(
   ID                   varchar(32) not null,
   USERID               varchar(32),
   ICCB                 int(11),
   STATUS               int(1),
   DELETED              int(1),
   primary key (ID)
);

alter table BLACKLIST add constraint FK_Reference_11 foreign key (USERID)
      references USER (ID) on delete restrict on update restrict;

alter table COMPLAINTS add constraint FK_Reference_10 foreign key (COMPLAINT_USERID)
      references USER (ID) on delete restrict on update restrict;

alter table COMPLAINTS add constraint FK_Reference_9 foreign key (LINK_SHOPSID)
      references SHOPS_HISTORY (ID) on delete restrict on update restrict;

alter table ENTRUST_LEASE add constraint FK_Reference_12 foreign key (USERID)
      references AGENT_USERINFO (USERID) on delete restrict on update restrict;

alter table ENTRUST_LEASE add constraint FK_Reference_13 foreign key (SHOPSID)
      references SHOPS_HISTORY (ID) on delete restrict on update restrict;

alter table ICCB_TRANSACTION add constraint FK_Reference_2 foreign key (WALLETID)
      references USER_WALLET (ID) on delete restrict on update restrict;

alter table SHOPS_COMMENTS add constraint FK_Reference_7 foreign key (SHOPSID)
      references SHOPS_HISTORY (ID) on delete restrict on update restrict;

alter table SHOPS_COMMENTS add constraint FK_Reference_8 foreign key (USERID)
      references USER (ID) on delete restrict on update restrict;

alter table SHOPS_HISTORY add constraint FK_Reference_5 foreign key (BASE_SHOPSID)
      references SHOPS (ID) on delete restrict on update restrict;

alter table SHOPS_PHOTOS_INFO add constraint FK_Reference_6 foreign key (SHOPSID)
      references SHOPS_HISTORY (ID) on delete restrict on update restrict;

alter table TRANSACTION add constraint FK_Reference_3 foreign key (USERID)
      references USER (ID) on delete restrict on update restrict;

alter table USER_COLLECTION add constraint FK_Reference_4 foreign key (USERID)
      references USER (ID) on delete restrict on update restrict;

alter table USER_WALLET add constraint FK_Reference_1 foreign key (USERID)
      references USER (ID) on delete restrict on update restrict;

