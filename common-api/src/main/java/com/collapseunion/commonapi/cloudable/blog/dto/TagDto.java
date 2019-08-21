package com.collapseunion.commonapi.cloudable.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 文章标签DTO
 *
 * @author CloudSen
 */
@Data
@Accessors(chain = true)
public class TagDto {

    @NotEmpty(message = "标签ID不能为空")
    private String uuid;
    @NotEmpty(message = "标签名不能为空")
    private String name;
    @NotEmpty(message = "标签颜色css不能为空")
    private String color;
}
