package com.collapseunion.commonapi.cloudable.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章标签表
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * UUID
     */
    @TableId(value = "uuid", type = IdType.UUID)
    private String uuid;

    /**
     * 标签名
     */
    private String name;

    /**
     * 标签颜色class
     */
    private String color;

    /**
     * 是否删除，1已删除，0未删除
     */
    private Boolean deleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
