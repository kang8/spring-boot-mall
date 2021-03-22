CREATE TABLE `user`
(
    `user_id`     bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `username`    varchar(50)  NOT NULL DEFAULT '' COMMENT '用户名',
    `phone`       char(11)     NOT NULL DEFAULT '' COMMENT '手机号',
    `password`    varchar(80)  NOT NULL DEFAULT '' COMMENT '密码',
    `address`     varchar(100) NOT NULL DEFAULT '' COMMENT '收货地址',
    `is_deleted`  tinyint      NOT NULL DEFAULT 0 COMMENT '注销标识字段(0-正常 1-已注销)',
    `is_locked`   tinyint      NOT NULL DEFAULT 0 COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    UNIQUE KEY (`phone`)
) COMMENT '用户表';

INSERT INTO `user` (username, phone, password)
VALUES ("yikang", "18012344321", "$2a$13$8R92g7dM/HZ4XTQx3VqLjOqCgya0QV7BYSt0swIqPEPMQisfdQNPC"),
       ("test1", "11111111111", "$2a$13$fQ.WLRhhhRqrVrt/YGPzBeBUyejYiY1xwaI/Xt6AuOCX2S4cY/4pm"),
       ("test2", "22222222222", "$2a$13$giO3Fl.wBjKR5h9Keo6j5ugk2S60gLWcC8jlVu28A38epV1Xvlr7."),
       ("test3", "33333333333", "$2a$13$DDkRROhOVd/1D0HKPlaC6eaBlSwxFG5XqqGL2U1WKzxpaxWPPhUfu");

