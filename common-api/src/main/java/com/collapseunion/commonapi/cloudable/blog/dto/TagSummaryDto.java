package com.collapseunion.commonapi.cloudable.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 文章标签摘要DTO
 *
 * @author CloudSen
 */
@Data
@Accessors(chain = true)
public class TagSummaryDto {

    @NotEmpty(message = "标签ID不能为空")
    private String uuid;
}
