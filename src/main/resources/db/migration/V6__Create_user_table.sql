CREATE TABLE `user`
(
    `user_id`     bigint unsigned PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `username`    varchar(50)  NOT NULL COMMENT '用户名',
    `phone`       char(11)     NOT NULL COMMENT '手机号',
    `password`    varchar(80)  NOT NULL COMMENT '密码',
    `address`     varchar(100) NOT NULL DEFAULT '' COMMENT '收货地址',
    `is_deleted`  boolean      NOT NULL DEFAULT false COMMENT '注销标识字段(false-正常 true-已注销) 默认正常',
    `is_locked`   boolean      NOT NULL DEFAULT false COMMENT '锁定标识字段(false-未锁定 true-已锁定) 默认未锁定',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    UNIQUE KEY (`phone`)
) COMMENT '用户表';

INSERT INTO `user` (username, phone, password, address)
VALUES ("yikang", "18012344321", "$2a$13$8R92g7dM/HZ4XTQx3VqLjOqCgya0QV7BYSt0swIqPEPMQisfdQNPC", '四川 成都市 双流区 东升街道 瑞吉路199号白河左岸'),
       ("test1", "11111111111", "$2a$13$fQ.WLRhhhRqrVrt/YGPzBeBUyejYiY1xwaI/Xt6AuOCX2S4cY/4pm", '中国'),
       ("test2", "22222222222", "$2a$13$giO3Fl.wBjKR5h9Keo6j5ugk2S60gLWcC8jlVu28A38epV1Xvlr7.", '中国'),
       ("test3", "33333333333", "$2a$13$DDkRROhOVd/1D0HKPlaC6eaBlSwxFG5XqqGL2U1WKzxpaxWPPhUfu", '中国');

