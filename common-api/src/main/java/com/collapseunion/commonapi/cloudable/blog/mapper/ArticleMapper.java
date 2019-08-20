package com.collapseunion.commonapi.cloudable.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

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
     * <p>
     * 获取所有文章摘要列表，包括评论数，和标签
     * </p>
     *
     * @return 所有文章摘要列表
     */
    List<ArticleSummaryDto> listAllArticleSummary();

    /**
     * <p>
     * 分页查询文章摘要，不给查询条件就是分页全部文章摘要
     * </p>
     *
     * @param page      分页参数
     * @param condition 查询条件
     * @return 分页后的数据
     */
    IPage<ArticleSummaryDto> pageArticleSummaryByCondition(Page<ArticleSummaryDto> page, @Param("condition") ArticleSummaryDto condition);

    /**
     * <p>
     * 根据条件计算文章的条数，用于一对多分页查询。
     * </p>
     *
     * @param condition 查询条件
     * @return 文章条数
     */
    Integer countArticleByCondition(@Param("condition") ArticleSummaryDto condition);
}
