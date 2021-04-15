CREATE TABLE `order_item`
(
    `order_item_id`     bigint unsigned PRIMARY KEY AUTO_INCREMENT COMMENT '订单关联购物项主键id',
    `order_id`          bigint unsigned NOT NULL COMMENT '订单主键id',
    `goods_id`          bigint unsigned NOT NULL COMMENT '关联商品id',
    `goods_name`        varchar(200)    NOT NULL COMMENT '下单时商品的名称(订单快照)',
    `goods_cover_image` varchar(200)    NOT NULL COMMENT '下单时商品的主图(订单快照)',
    `selling_price`     decimal(19, 2)  NOT NULL COMMENT '下单时商品的价格(订单快照)',
    `goods_count`       int unsigned    NOT NULL COMMENT '数量',
    `create_time`       datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX (`order_id`)
) COMMENT '订单详情表';
