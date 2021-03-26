package com.kang.mall.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kang.mall.util.CommonUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yikang
 * ClassName: BaseEntity
 * Description: 基础的实体类，包含公共的实体属性
 * Create Date: 2021/2/26 20:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseEntity extends BaseTimeEntity implements Serializable {

    /**
     * 创建用户 Id
     */
    protected Long createUser;
    /**
     * 更新用户 id
     */
    protected Long updateUser;
    /**
     * 创建时间
     * <p>
     * JsonDeserialize 反序列化规则
     * JsonSerialize 序列化规则
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    protected LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    protected LocalDateTime updateTime;

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public void setUpdateUser(HttpSession session) {
        Long adminUserId = CommonUtils.getAdminUserId(session);
        setUpdateUser(adminUserId);
    }

    public void setCreateUserAndUpdateUser(HttpSession session) {
        Long adminUserId = CommonUtils.getAdminUserId(session);
        setCreateUser(adminUserId);
        setUpdateUser(adminUserId);
    }
}
