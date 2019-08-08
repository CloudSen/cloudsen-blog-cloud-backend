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
 * 文章评论表
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * UUID
     */
    @TableId(value = "uuid", type = IdType.UUID)
    private String uuid;

    /**
     * 评论人
     */
    private String userName;

    /**
     * 个人网站
     */
    private String userUrl;

    /**
     * 评论内容，可以是MD格式
     */
    private String content;

    /**
     * 文章UUID
     */
    private String articleId;

    /**
     * 是否删除，1已删除，0未删除
     */
    private Boolean deleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
