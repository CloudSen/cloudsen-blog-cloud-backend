package com.cloudable.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudable.blog.service.IArticleService;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import com.collapseunion.commonapi.cloudable.blog.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public List<ArticleSummaryDto> listAllArticleSummary() {
        return this.baseMapper.listAllArticleSummary();
    }

    @Override
    public IPage<ArticleSummaryDto> pageArticleSummaryByCondition(Page<ArticleSummaryDto> page, ArticleSummaryDto condition) {
        page.setOptimizeCountSql(false);
        IPage<ArticleSummaryDto> articleSummaryDtoIPage = this.baseMapper.pageArticleSummaryByCondition(page, condition);
        Integer articleTotal = this.baseMapper.countArticleByCondition(condition);
        articleSummaryDtoIPage.setTotal(articleTotal);
        return articleSummaryDtoIPage;
    }
}
