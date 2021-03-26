CREATE TABLE `cart`
(
    `cart_id` bigint PRIMARY KEY AUTO_INCREMENT COMMENT '购物车主键id',
    `user_id`      bigint   NOT NULL COMMENT '用户主键id',
    `goods_id`     bigint   NOT NULL COMMENT '关联商品id',
    `goods_count`  int      NOT NULL DEFAULT 1 COMMENT '数量',
    `is_deleted`   int      NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
    `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间'
);
