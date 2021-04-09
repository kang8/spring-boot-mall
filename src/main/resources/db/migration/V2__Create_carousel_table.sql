CREATE TABLE `carousel`
(
    `carousel_id`   bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '首页轮播图主键id',
    `carousel_url`  varchar(100)    NOT NULL COMMENT '轮播图',
    `redirect_url`  varchar(100)    NOT NULL DEFAULT '##' COMMENT '点击后的跳转地址(默认不跳转)',
    `carousel_rank` int unsigned    NOT NULL DEFAULT '0' COMMENT '排序值(字段越大越靠前)',
    `is_deleted`    boolean         NOT NULL DEFAULT false COMMENT '删除标识字段(false-未删除 true-已删除)',
    `create_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_user`   bigint unsigned NOT NULL COMMENT '创建者id',
    `update_user`   bigint unsigned NOT NULL COMMENT '修改者id'
) COMMENT '轮播图';

INSERT INTO `carousel`(`carousel_url`, `create_user`, `update_user`)
VALUES ('https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner1.png', 1, 1),
       ('https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.png', 1, 1),
       ('https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner3.png', 1, 1),
       ('https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner4.jpg', 1, 1)
