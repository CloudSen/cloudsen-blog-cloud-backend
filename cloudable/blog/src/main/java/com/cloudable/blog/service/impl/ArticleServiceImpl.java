package com.cloudable.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudable.blog.service.IArticleService;
import com.cloudable.blog.service.IArticleTagService;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleModifyDto;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import com.collapseunion.commonapi.cloudable.blog.entity.ArticleTag;
import com.collapseunion.commonapi.cloudable.blog.mapper.ArticleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Service
@Transactional
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
    public IPage<ArticleSummaryDto> pageArticleSummaryByCondition(Page<ArticleSummaryDto> page, ArticleSummaryDto condition) {
        page.setOptimizeCountSql(false);
        IPage<ArticleSummaryDto> articleSummaryDtoPage = this.baseMapper.pageArticleSummaryByCondition(page, condition);
        Integer articleTotal = this.baseMapper.countArticleByCondition(condition);
        articleSummaryDtoPage.setTotal(articleTotal);
        return articleSummaryDtoPage;
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
}
