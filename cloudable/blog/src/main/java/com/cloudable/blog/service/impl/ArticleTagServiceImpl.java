package com.cloudable.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudable.blog.service.IArticleTagService;
import com.collapseunion.commonapi.cloudable.blog.dto.TagDto;
import com.collapseunion.commonapi.cloudable.blog.entity.ArticleTag;
import com.collapseunion.commonapi.cloudable.blog.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章-标签多对多关系表 服务实现类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements IArticleTagService {

    @Override
    public List<TagDto> getTagByArticleId(String articleId) {
        return this.baseMapper.getTagByArticleId(articleId);
    }
}
