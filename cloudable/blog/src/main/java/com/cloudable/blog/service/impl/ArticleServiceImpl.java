package com.cloudable.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudable.blog.service.IArticleService;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import com.collapseunion.commonapi.cloudable.blog.mapper.ArticleMapper;
import com.collapseunion.commonutils.EntityAndDtoConversion;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public List<ArticleSummaryDto> listAllArticleSummary() {
        return this.baseMapper.listAllArticleSummary();
    }

    @Override
    public IPage<ArticleSummaryDto> pageArticleSummaryByCondition(Page<Article> page, ArticleSummaryDto condition) {
        LambdaQueryWrapper<Article> query = Wrappers.lambdaQuery();
        query.select(Article.class, tableFieldInfo ->
                !"content".equals(tableFieldInfo.getColumn())
                        && !"imgUrl".equals(tableFieldInfo.getColumn())
                        && !"deleted".equals(tableFieldInfo.getColumn()))
                .eq(StringUtils.isNotEmpty(condition.getUuid()), Article::getUuid, condition.getUuid())
                .like(StringUtils.isNotEmpty(condition.getTitle()), Article::getTitle, condition.getTitle())
                .like(StringUtils.isNotEmpty(condition.getSummary()), Article::getSummary, condition.getSummary())
                .apply(condition.getCreateTime() != null,
                        "DATE_FORMAT(create_time, '%Y-%m-%d') = {0}",
                        Objects.requireNonNull(condition.getCreateTime())
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return EntityAndDtoConversion.pageEntity2Dto(this.page(page, query), ArticleSummaryDto.class);
    }
}
