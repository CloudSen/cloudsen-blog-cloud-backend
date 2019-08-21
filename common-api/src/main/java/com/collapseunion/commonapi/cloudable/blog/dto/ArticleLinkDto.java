package com.collapseunion.commonapi.cloudable.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上一篇，下一篇文章链接DTO
 *
 * @author CloudSen
 */
@Data
@Accessors(chain = true)
public class ArticleLinkDto {

    private String uuid;
    private String title;
}
