package com.collapseunion.commonapi.cloudable.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章详情DTO
 *
 * @author CloudSen
 */
@Data
@Accessors(chain = true)
public class ArticleDetailDto {

    private String uuid;
    private String title;
    private String summary;
    private String content;
    private String imgUrlMd;
    private String imgUrl;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<TagDto> tags;
    /**
     * 文章是否已过期
     */
    private Boolean deprecated = false;
    /**
     * 上一篇文章
     */
    private ArticleLinkDto previousArticle;
    /**
     * 下一篇文章
     */
    private ArticleLinkDto nextArticle;
}
