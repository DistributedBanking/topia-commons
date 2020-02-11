/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/2/6 0:16:48                             */
/*==============================================================*/


drop table if exists acquireii.t_acquire_order;

drop table if exists acquireii.t_amount_detail;

drop table if exists acquireii.t_goods_detail;

drop table if exists acquireii.t_payment_info;

drop table if exists acquireii.t_pcc_content;

drop table if exists acquireii.t_pcc_content_param;

drop table if exists acquireii.t_refund_order;

drop table if exists acquireii.t_request_identity;

drop table if exists acquireii.t_scene_param;

drop table if exists acquireii.t_terminal_detail;

/*==============================================================*/
/* Table: t_acquire_order                                       */
/*==============================================================*/
create table acquireii.t_acquire_order
(
   global_id            bigint not null comment '全局号',
   request_no           varchar(200) not null comment '等同于requestIdentity.requestNo.查询方便',
   voucher_no           varchar(200) comment '凭证号',
   partner_id           varchar(200) not null comment '发起方memberId',
   product_code         varchar(200) not null comment '产品码',
   currency_code        varchar(50) not null comment '收单币种',
   amount               decimal(19,4) not null comment '收单金额',
   payee_mid            varchar(200) not null comment '收款人mid',
   payer_mid            varchar(200) comment '付款人mid',
   subject              varchar(200) comment '标题',
   expired_time         timestamp(3) comment '过期时间',
   notify_url           varchar(500) comment '商户通知地址',
   scene_code           varchar(32) not null comment '支付场景',
   status               varchar(200) not null comment '收单状态',
   submitted_time       timestamp(3) comment '提交时间',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    TIMESTAMP(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本',
   primary key (global_id)
);

alter table acquireii.t_acquire_order comment '收单数据';

/*==============================================================*/
/* Index: i_ao_lut                                              */
/*==============================================================*/
create index i_ao_lut on acquireii.t_acquire_order
(
   last_updated_time
);

/*==============================================================*/
/* Index: i_ao_st                                               */
/*==============================================================*/
create index i_ao_st on acquireii.t_acquire_order
(
   submitted_time
);

/*==============================================================*/
/* Table: t_amount_detail                                       */
/*==============================================================*/
create table acquireii.t_amount_detail
(
   global_id            bigint not null comment '全局号',
   net_amount           decimal(19,4),
   net_currency_code    varchar(50) comment '币种',
   discountable_amount  decimal(19,4),
   discountable_currency_code varchar(50) comment '币种',
   tip_amount           decimal(19,4),
   tip_currency_code    varchar(50) comment '币种',
   vat_amount           decimal(19,4),
   vat_currency_code    varchar(50) comment '币种',
   primary key (global_id)
);

alter table acquireii.t_amount_detail comment '金额信息';

/*==============================================================*/
/* Table: t_goods_detail                                        */
/*==============================================================*/
create table acquireii.t_goods_detail
(
   global_id            bigint not null comment '全局号',
   goods_id             varchar(200),
   goods_name           varchar(200),
   price                decimal(19,4),
   price_currency_code  varchar(50) not null comment '币种',
   quantity             decimal(19,4),
   goods_category       varchar(200),
   categories_tree      varchar(200),
   body                 varchar(200),
   show_url             varchar(500),
   primary key (global_id)
);

alter table acquireii.t_goods_detail comment '商品信息';

/*==============================================================*/
/* Table: t_payment_info                                        */
/*==============================================================*/
create table acquireii.t_payment_info
(
   global_id            bigint not null comment '全局号',
   paid_time            TIMESTAMP(3) not null comment '支付成功时间',
   pay_channel          varchar(200) not null comment '支付渠道',
   payer_fee_currency_code varchar(50) not null comment '收单币种',
   payer_fee_amount     decimal(19,4) not null comment '收单金额',
   payee_fee_currency_code varchar(50) not null comment '结算币种',
   payee_fee_amount     decimal(19,4) not null comment '结算金额',
   settlement_currency_code varchar(50) comment '结算币种',
   settlement_amount    decimal(19,4) comment '结算金额',
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    TIMESTAMP(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本',
   primary key (global_id)
);

alter table acquireii.t_payment_info comment '收单数据';

/*==============================================================*/
/* Table: t_pcc_content                                         */
/*==============================================================*/
create table acquireii.t_pcc_content
(
   global_id            bigint not null comment '全局id',
   pcc_final            varchar(200) not null comment '授权码字符串',
   pay_method           varchar(200) comment '选择的支付渠道',
   risk_level           varchar(200) not null comment '风险级别',
   primary key (global_id)
);

alter table acquireii.t_pcc_content comment '支付授权凭证';

/*==============================================================*/
/* Table: t_pcc_content_param                                   */
/*==============================================================*/
create table acquireii.t_pcc_content_param
(
   global_id            bigint not null comment '通用指令id',
   pkey                 VARCHAR(200) not null comment '键',
   value                VARCHAR(200) not null comment '值',
   primary key (global_id, pkey)
);

alter table acquireii.t_pcc_content_param comment '授权代码参数';

/*==============================================================*/
/* Table: t_refund_order                                        */
/*==============================================================*/
create table acquireii.t_refund_order
(
   global_id            bigint not null comment '退款全局号',
   partner_id           varchar(200) not null comment '发起方memberId',
   acquire_global_id    bigint not null comment '收单全局号',
   currency_code        varchar(50) not null comment '收单币种',
   amount               decimal(19,4) not null comment '收单金额',
   reason               varchar(500) comment '原因',
   notify_url           varchar(500) comment '商户通知地址',
   status               varchar(200) not null,
   operator_name        varchar(200),
   created_time         timestamp(3) not null comment '创建时间',
   last_updated_time    timestamp(3) not null comment '最后更新时间',
   data_version         bigint not null comment '数据版本',
   primary key (global_id)
);

alter table acquireii.t_refund_order comment '收单数据';

/*==============================================================*/
/* Index: i_ao_lut                                              */
/*==============================================================*/
create index i_ao_lut on acquireii.t_refund_order
(
   last_updated_time
);

/*==============================================================*/
/* Index: i_ao_ag                                               */
/*==============================================================*/
create index i_ao_ag on acquireii.t_refund_order
(
   acquire_global_id
);

/*==============================================================*/
/* Table: t_request_identity                                    */
/*==============================================================*/
create table acquireii.t_request_identity
(
   global_id            bigint not null comment '退款全局号',
   issuer_type          varchar(200) not null comment '发起人类型',
   issuer_code          varchar(200) not null comment '发起人代码',
   request_no           varchar(200) not null comment '商户订单号',
   created_time         timestamp(3) not null comment '创建时间',
   primary key (global_id)
);

alter table acquireii.t_request_identity comment '请求标识';

/*==============================================================*/
/* Index: i_ri_isrno                                            */
/*==============================================================*/
create index i_ri_isrno on acquireii.t_request_identity
(
   issuer_type,
   issuer_code,
   request_no
);

/*==============================================================*/
/* Table: t_scene_param                                         */
/*==============================================================*/
create table acquireii.t_scene_param
(
   global_id            bigint not null comment '通用指令id',
   pkey                 VARCHAR(200) not null comment '键',
   value                VARCHAR(200) not null comment '值',
   primary key (global_id, pkey)
);

alter table acquireii.t_scene_param comment '场景参数';

/*==============================================================*/
/* Table: t_terminal_detail                                     */
/*==============================================================*/
create table acquireii.t_terminal_detail
(
   global_id            bigint not null comment '全局号',
   operator_id          varchar(200),
   terminal_id          varchar(200),
   store_id             varchar(200),
   store_name           varchar(200),
   merchant_name        varchar(200),
   location             varchar(200),
   primary key (global_id)
);

alter table acquireii.t_terminal_detail comment '终端信息';

