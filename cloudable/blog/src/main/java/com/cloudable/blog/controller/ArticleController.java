package com.cloudable.blog.controller;


import com.cloudable.blog.BlogApplication;
import com.cloudable.blog.service.IArticleService;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import com.collapseunion.commonutils.globalresult.Result;
import com.collapseunion.commonutils.globalresult.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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

    @GetMapping("")
    public Result<Collection<Article>> getAllArticles() {
        return ResultUtil.ok(this.articleService.list(), BlogApplication.APPLICATION_NAME);
    }
}
