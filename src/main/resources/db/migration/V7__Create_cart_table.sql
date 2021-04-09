CREATE TABLE `cart`
(
    `cart_id`     bigint unsigned PRIMARY KEY AUTO_INCREMENT COMMENT '购物车主键id',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户主键id',
    `goods_id`    bigint unsigned NOT NULL COMMENT '关联商品id',
    `goods_count` int unsigned    NOT NULL DEFAULT 1 COMMENT '数量 默认为 1',
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
    INDEX (`goods_id`),
    INDEX (`user_id`)
) COMMENT '购物车';
