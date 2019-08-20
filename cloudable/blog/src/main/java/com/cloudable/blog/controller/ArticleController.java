package com.cloudable.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudable.blog.BlogApplication;
import com.cloudable.blog.service.IArticleService;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleModifyDto;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonutils.JsonUtil;
import com.collapseunion.commonutils.globalresult.Result;
import com.collapseunion.commonutils.globalresult.ResultUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 直接获取所有文章摘要
     *
     * @return 所有文章摘要列表
     */
    @GetMapping("")
    public Result<Collection<ArticleSummaryDto>> listAllArticleSummary() {
        return ResultUtil.ok(this.articleService.listAllArticleSummary(), BlogApplication.APPLICATION_NAME);
    }

    /**
     * 分页查询文章摘要，不给查询条件就是分页全部文章摘要
     *
     * @return 当前页文章摘要列表
     */
    @PostMapping("/page")
    public Result<IPage<ArticleSummaryDto>> pageArticleSummaryByCondition(@RequestBody Map<String, Map<String, Object>> params) {
        ArticleSummaryDto condition = JsonUtil.map2Obj(params.get("condition"), ArticleSummaryDto.class);
        Page<ArticleSummaryDto> page = JsonUtil.map2MybatisPage(params.get("page"), ArticleSummaryDto.class);
        return ResultUtil.ok(
                this.articleService.pageArticleSummaryByCondition(page, condition),
                BlogApplication.APPLICATION_NAME
        );
    }

    @PostMapping("")
    public Result<String> saveOrUpdateArticle(@Validated @RequestBody ArticleModifyDto articleModifyDto) {
        return ResultUtil.ok(
                this.articleService.createOrUpdateArticle(articleModifyDto),
                BlogApplication.APPLICATION_NAME
        );
    }
}
