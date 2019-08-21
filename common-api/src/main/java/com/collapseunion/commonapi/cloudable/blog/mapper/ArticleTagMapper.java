package com.collapseunion.commonapi.cloudable.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.collapseunion.commonapi.cloudable.blog.dto.TagDto;
import com.collapseunion.commonapi.cloudable.blog.entity.ArticleTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章-标签多对多关系表 Mapper 接口
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    /**
     * 根据文章ID查询对应的文章标签
     *
     * @param articleId 文章UUID
     * @return 对应的文章标签列表
     */
    List<TagDto> getTagByArticleId(@Param("articleId") String articleId);
}
