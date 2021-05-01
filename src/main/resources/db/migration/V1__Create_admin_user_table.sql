CREATE TABLE `admin_user`
(
    `admin_user_id` bigint unsigned AUTO_INCREMENT PRIMARY KEY COMMENT '管理员id',
    `username`      varchar(50) NOT NULL COMMENT '管理员登陆名称',
    `password`      varchar(80) NOT NULL COMMENT '管理员登陆密码',
    `nick_name`     varchar(50) NOT NULL default '' COMMENT '管理员显示昵称',
    `locked`        boolean     NOT NULL DEFAULT false COMMENT '是否锁定 false->未锁定 true->已锁定无法登陆',
    UNIQUE KEY (`username`)
) COMMENT '管理员表';

INSERT INTO `admin_user` (username, password, nick_name)
-- password yikang
VALUES ("admin", "$2a$13$8R92g7dM/HZ4XTQx3VqLjOqCgya0QV7BYSt0swIqPEPMQisfdQNPC", "yikang"),
-- password 123456
       ("test1", "$2a$13$fQ.WLRhhhRqrVrt/YGPzBeBUyejYiY1xwaI/Xt6AuOCX2S4cY/4pm", "test1"),
       ("test2", "$2a$13$giO3Fl.wBjKR5h9Keo6j5ugk2S60gLWcC8jlVu28A38epV1Xvlr7.", "test2"),
       ("test3", "$2a$13$DDkRROhOVd/1D0HKPlaC6eaBlSwxFG5XqqGL2U1WKzxpaxWPPhUfu", "test3"),
       ("test4", "$2a$13$aegYQN4IRJnXYZhd5PhOQu.qV7/CbgLxgA66M3TK6pLgh/NlFQ8PO", "test4"),
       ("test5", "$2a$13$X.2wi9NNBuDDsG.o1Z3F3OTuL.LPRXFk3qrde6jWjFwW27PkEi0Pe", "test5"),
       ("test6", "$2a$13$PKJfCvahPagvuLUd8jCpd.TCkT94JQWCHFgNSQ/gMnj7tIY9278Nm", "test6"),
       ("test7", "$2a$13$TdJ2USF/S6knJ4/NELGRsucRM8VVun5Y9H/bAs8pZj1ZlbRINrnRu", "test7"),
       ("test8", "$2a$13$HVGepO6.3VnptmttDofAgOR2C87.yD/SwO9TYKMEG2s4uyIRQEmZO", "test8"),
       ("test9", "$2a$13$.DEDQjl.mjmaEbScuFy8OeigWf3Xc1P7HjkQp84W9BsBsvvTo7ZzS", "test9"),
       ("test10", "$2a$13$G2En3/tctK0CKP5Ra4IkjuBcxVDIVKG6F8nv3dKmpBA3z7yop02.e", "test10");
