package com.cloudable.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudable.blog.service.IArticleService;
import com.cloudable.blog.service.IArticleTagService;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleDetailDto;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleModifyDto;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import com.collapseunion.commonapi.cloudable.blog.entity.ArticleTag;
import com.collapseunion.commonapi.cloudable.blog.mapper.ArticleMapper;
import com.collapseunion.commonutils.customexceptions.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    private final IArticleTagService articleTagService;

    public ArticleServiceImpl(IArticleTagService articleTagService) {
        this.articleTagService = articleTagService;
    }

    @Override
    public List<ArticleSummaryDto> listAllArticleSummary() {
        return this.baseMapper.listAllArticleSummary();
    }

    @Override
    public IPage<ArticleSummaryDto> pageArticleSummaryAndTagByCondition(Page<ArticleSummaryDto> page, ArticleSummaryDto condition) {
        // 先对文章进行分页查询
        page.setOptimizeCountSql(false);
        page.setSearchCount(false);
        IPage<ArticleSummaryDto> articleSummaryDtoPage = this.baseMapper.pageArticleSummaryAndTagByCondition(page, condition);
        Integer articleTotal = this.baseMapper.countArticleByCondition(condition);
        articleSummaryDtoPage.setTotal(articleTotal);
        return articleSummaryDtoPage;
    }

    @Override
    public IPage<ArticleSummaryDto> pageArticleSummaryByCondition(Page<ArticleSummaryDto> page, ArticleSummaryDto condition) {
        // 先对文章进行分页查询
        IPage<ArticleSummaryDto> articlePage = this.baseMapper.pageArticleSummaryByCondition(page, condition);
        List<ArticleSummaryDto> articles = articlePage.getRecords();
        if (CollectionUtils.isNotEmpty(articles)) {
            articles.forEach(articleSummaryDto -> {
                articleSummaryDto.setTags(this.articleTagService.getTagByArticleId(articleSummaryDto.getUuid()));
            });
        }
        articlePage.setRecords(articles);
        return articlePage;
    }

    @Override
    public String createOrUpdateArticle(ArticleModifyDto articleModifyDto) {
        // 保存文章, 返回id
        Article article = articleModifyDto.toArticle();
        boolean isUpdate = StringUtils.isNotEmpty(article.getUuid());
        saveOrUpdate(article);
        // 再保存文章和标签的关系, 如果是更新文章，则重建关系
        if (isUpdate) {
            articleTagService.remove(Wrappers.<ArticleTag>lambdaQuery().eq(ArticleTag::getArticleId, article.getUuid()));
        }
        articleTagService.saveBatch(articleModifyDto.toArticleTags(article.getUuid()));
        return article.getUuid();
    }

    @Override
    public ArticleDetailDto findArticleById(String articleId) {
        ArticleDetailDto articleDetailDto = Optional.ofNullable(this.baseMapper.findArticleById(articleId))
                .orElseThrow(ArticleNotFoundException::new);
        LocalDateTime updateTime = articleDetailDto.getUpdateTime();
        LocalDateTime today = LocalDateTime.now();
        long durationDays = Duration.between(updateTime, today).toDays();
        if (durationDays > Constants.WARN_DURATION_DAYS) {
            articleDetailDto.setDeprecated(true);
        }
        // TODO 根据创建时间，得到上一篇文章和下一篇文章
        log.info(Constants.ARTICLE_DURATION_DAYS, articleDetailDto.getTitle(), durationDays);
        log.info(Constants.ARTICLE_CONTENT, articleDetailDto.getTitle(), articleDetailDto.getContent());
        return articleDetailDto;
    }
}
