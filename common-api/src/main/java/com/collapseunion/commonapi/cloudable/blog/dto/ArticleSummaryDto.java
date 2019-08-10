package com.collapseunion.commonapi.cloudable.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章摘要数据传输类
 *
 * @author CloudSen
 */
@Data
@Accessors(chain = true)
public class ArticleSummaryDto {

    private String uuid;
    private String title;
    private String summary;
    private String imgUrlMd;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer commentCount;
    private List<TagDto> tags;
}
