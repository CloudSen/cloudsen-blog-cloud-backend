package com.collapseunion.commonapi.cloudable.blog.mapper;

import com.cloudable.blog.BlogApplication;
import com.collapseunion.commonapi.cloudable.blog.dto.ArticleSummaryDto;
import com.collapseunion.commonapi.cloudable.blog.entity.Article;
import com.collapseunion.commonapi.cloudable.blog.entity.ArticleTag;
import com.collapseunion.commonapi.cloudable.blog.entity.Tag;
import com.collapseunion.commonutils.EntityAndDtoConversion;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Test
    public void getList() {
        List<Article> articles = this.articleMapper.selectList(null);
        System.out.println("Original: ");
        articles.forEach(System.out::println);
        Assertions.assertThat(articles).isNotEmpty();
        System.out.println("DTO: ");
        List<ArticleSummaryDto> articleSummaryDtos = EntityAndDtoConversion.listEntity2Dto(articles, ArticleSummaryDto.class);
        articleSummaryDtos.forEach(System.out::println);
        Assertions.assertThat(articleSummaryDtos).hasSameSizeAs(articles);
    }

    @Test
    public void createNewArticle() {
        Article article = new Article()
                .setTitle("Test Create Article")
                .setSummary("Test Create Article. Hello World!")
                .setContent("Test Create Article. Hello World! Fuck this, fuck that.")
                .setImgUrl("https://test.com")
                .setImgUrlMd("https://test-md.com");
        int insertRows = this.articleMapper.insert(article);
        Assertions.assertThat(insertRows).isEqualTo(1);
    }

    @Test
    public void createNewTag() {
        Tag tag = new Tag()
                .setColor("light-green accent-2")
                .setName("VUE");
        int insertRows = this.tagMapper.insert(tag);
        Assertions.assertThat(insertRows).isEqualTo(1);
    }

    @Test
    public void associateArticleAndTag() {
        ArticleTag articleTag = new ArticleTag()
                .setArticleId("c643f124c139bb4b31f15247fd38aa83")
                .setTagId("76f86a7e842ede69627e3f76b4a46392");
        int insertRows = this.articleTagMapper.insert(articleTag);
        Assertions.assertThat(insertRows).isEqualTo(1);
    }
}