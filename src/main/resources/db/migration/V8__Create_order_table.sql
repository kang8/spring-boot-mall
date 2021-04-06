CREATE TABLE `order`
(
    `order_id`     bigint PRIMARY KEY AUTO_INCREMENT COMMENT '订单表主键id',
    `user_id`      bigint         NOT NULL COMMENT '用户主键id',
    `total_price`  decimal(19, 2) NOT NULL COMMENT '订单总价',
    `pay_status`   tinyint        NOT NULL DEFAULT 0 COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败。默认未支付',
    `pay_type`     tinyint        NOT NULL DEFAULT 0 COMMENT '支付方式:0.无 1.支付宝支付 2.微信支付 3.银行卡',
    `order_status` tinyint        NOT NULL DEFAULT 0 COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭。默认待支付',
    `username`     varchar(50)    NOT NULL COMMENT '收货人姓名',
    `phone`        char(11)       NOT NULL COMMENT '收货人手机号',
    `address`      varchar(100)   NOT NULL COMMENT '收货人收货地址',
    `is_deleted`   tinyint        NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除) 默认未删除',
    `create_time`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间'
)COMMENT '订单表';
