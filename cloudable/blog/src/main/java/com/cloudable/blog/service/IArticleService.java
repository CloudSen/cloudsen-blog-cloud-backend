package com.cloudable.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleModifyDto;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
public interface IArticleService extends IService<Article> {

    /**
     * <p>
     * 查询所有文章摘要列表
     * </p>
     *
     * @return 所有文章摘要列表
     */
    List<ArticleSummaryDto> listAllArticleSummary();

    /**
     * <p>
     * 分页条件查询文章摘要和它的标签
     * </p>
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return 部分文章的摘要
     */
    IPage<ArticleSummaryDto> pageArticleSummaryAndTagByCondition(Page<ArticleSummaryDto> page, ArticleSummaryDto condition);

    /**
     * <p>
     * 分页条件查询文章摘要
     * </p>
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return 部分文章的摘要
     */
    IPage<ArticleSummaryDto> pageArticleSummaryByCondition(Page<ArticleSummaryDto> page, ArticleSummaryDto condition);

    /**
     * 保存新文章或更新旧文章，并返回ID
     *
     * @param articleModifyDto 新文章前台数据
     * @return 新文章的ID
     */
    String createOrUpdateArticle(ArticleModifyDto articleModifyDto);
}
