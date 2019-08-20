package com.collapseunion.commonapi.cloudable.blog.dto;

import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import com.collapseunion.commonapi.cloudable.blog.entity.ArticleTag;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 新建文章时的DTO
 *
 * @author CloudSen
 */
@Data
@Accessors(chain = true)
public class ArticleModifyDto {

    private String uuid;
    @NotEmpty(message = "文章标题不能为空")
    private String title;
    @NotEmpty(message = "文章摘要不能为空")
    private String summary;
    @NotEmpty(message = "文章内容不能为空")
    private String content;
    @NotEmpty(message = "文章主题缩略图不能为空")
    private String imgUrlMd;
    @NotEmpty(message = "文章主题图片不能为空")
    private String imgUrl;
    @Size(max = 3, min = 1, message = "文章标签至少1个，不超过3个")
    @Valid
    private List<TagSummaryDto> tags;

    /**
     * 将DTO转换为Article实体类
     *
     * @return Article实体类
     */
    public Article toArticle() {
        return new Article()
                .setUuid(this.uuid)
                .setTitle(this.title)
                .setSummary(this.summary)
                .setContent(this.content)
                .setImgUrlMd(this.imgUrlMd)
                .setImgUrl(this.imgUrl);
    }

    /**
     * 将tagDtos转换为Article和Tag关联的列表
     *
     * @param articleId 文章ID
     * @return ArticleTag实体类列表
     */
    public List<ArticleTag> toArticleTags(String articleId) {
        List<ArticleTag> articleTags = new ArrayList<>();
        tags.forEach(tagSummaryDto -> {
            ArticleTag articleTag = new ArticleTag()
                    .setArticleId(articleId)
                    .setTagId(tagSummaryDto.getUuid());
            articleTags.add(articleTag);
        });
        return articleTags;
    }
}
