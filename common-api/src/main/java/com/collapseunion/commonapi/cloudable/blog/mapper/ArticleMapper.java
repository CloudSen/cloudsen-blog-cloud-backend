package com.collapseunion.commonapi.cloudable.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 获取所有文章摘要列表，包括评论数，和标签
     *
     * @return 所有文章摘要列表
     */
    List<ArticleSummaryDto> listAllArticleSummary();
}
