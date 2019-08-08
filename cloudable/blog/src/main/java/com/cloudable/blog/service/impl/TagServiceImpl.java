package com.cloudable.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudable.blog.service.ITagService;
import com.collapseunion.commonapi.cloudable.blog.entity.Tag;
import com.collapseunion.commonapi.cloudable.blog.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
