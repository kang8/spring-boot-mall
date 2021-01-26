package com.kang.mall.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yikang
 * ClassName: AdminUserVO
 * Description:
 * Create Date: 2021/1/20 10:55
 */
@Data
public class AdminUserVO {
    private Long adminUserId;
    private String username;
    private String nickName;
    private Date createTime;
}
