package com.collapseunion.commonapi.cloudable.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章标签数据传输类
 *
 * @author CloudSen
 */
@Data
@Accessors(chain = true)
public class TagDto {

    private String uuid;
    private String name;
    private String color;
}
