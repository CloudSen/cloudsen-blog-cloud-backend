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
 * 文章表
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * UUID
     */
    @TableId(value = "uuid", type = IdType.UUID)
    private String uuid;

    /**
     * 标题
     */
    private String title;

    /**
     * 概述
     */
    private String summary;

    /**
     * 正文
     */
    private String content;

    /**
     * 预览大图片
     */
    private String imgUrl;

    /**
     * 预览中等图片
     */
    private String imgUrlMd;

    /**
     * 是否删除，1已删除，0未删除
     */
    private Boolean deleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
