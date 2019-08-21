package com.cloudable.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.collapseunion.commonapi.cloudable.blog.dto.TagDto;
import com.collapseunion.commonapi.cloudable.blog.entity.ArticleTag;

import java.util.List;

/**
 * <p>
 * 文章-标签多对多关系表 服务类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
public interface IArticleTagService extends IService<ArticleTag> {

    /**
     * 根据文章ID查询对应的文章标签
     *
     * @param articleId 文章UUID
     * @return 对应的文章标签列表
     */
    List<TagDto> getTagByArticleId(String articleId);
}
