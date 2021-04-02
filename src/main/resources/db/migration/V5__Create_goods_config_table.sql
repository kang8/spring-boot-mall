CREATE TABLE `goods_config`
(
    `config_id`   bigint AUTO_INCREMENT PRIMARY KEY COMMENT '商品配置项主键 id',
#     `config_name`  varchar(50)  NOT NULL DEFAULT '' COMMENT '显示字符',
    `config_type` tinyint  NOT NULL COMMENT '1-(首页)新品上线 2-(首页)热销商品 3-(首页)为你推荐',
    `goods_id`    bigint   NOT NULL COMMENT '商品 id 默认为 0。也就是没有商品与其关联',
    `config_rank` int      NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
    `is_deleted`  tinyint  NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)默认为未删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最新修改时间',
    `create_user` bigint   NOT NULL COMMENT '创建者id',
    `update_user` bigint   NOT NULL COMMENT '修改者id'
) COMMENT '商品配置表';

INSERT INTO `goods_config` (config_type, goods_id, create_user, update_user)
VALUES (1, 1, 1, 1),
       (1, 269, 1, 1),
       (1, 3, 1, 1),
       (1, 4, 1, 1),
       (1, 5, 1, 1),
       (2, 6, 1, 1),
       (2, 7, 1, 1),
       (2, 8, 1, 1),
       (2, 9, 1, 1),
       (2, 10, 1, 1),
       (3, 11, 1, 1),
       (3, 12, 1, 1),
       (3, 13, 1, 1),
       (3, 14, 1, 1),
       (3, 15, 1, 1);
