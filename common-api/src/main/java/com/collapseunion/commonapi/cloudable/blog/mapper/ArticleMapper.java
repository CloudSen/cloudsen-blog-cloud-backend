package com.collapseunion.commonapi.cloudable.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleDetailDto;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章表 Mapper 接口
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

    /**
     * 分页查询文章摘要和其标签，不给查询条件就是分页全部文章摘要和其标签
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页后的数据
     */
    IPage<ArticleSummaryDto> pageArticleSummaryAndTagByCondition(Page page, @Param("condition") ArticleSummaryDto condition);

    /**
     * 分页查询文章摘要, 不给查询条件就是分页全部文章摘要
     *
     * @param page
     * @param condition
     * @return
     */
    IPage<ArticleSummaryDto> pageArticleSummaryByCondition(Page page, @Param("condition") ArticleSummaryDto condition);

    /**
     * 根据条件计算文章的条数，用于一对多分页查询。
     *
     * @param condition 查询条件
     * @return 文章条数
     */
    Integer countArticleByCondition(@Param("condition") ArticleSummaryDto condition);

    /**
     * 根据文章ID查询详情
     *
     * @param uuid 文章UUID
     * @return 文章详情
     */
    ArticleDetailDto findArticleById(@Param("uuid") String uuid);
}
