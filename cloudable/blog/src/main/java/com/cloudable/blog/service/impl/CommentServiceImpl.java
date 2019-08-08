package com.cloudable.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudable.blog.service.ICommentService;
import com.collapseunion.commonapi.cloudable.blog.entity.Comment;
import com.collapseunion.commonapi.cloudable.blog.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章评论表 服务实现类
 * </p>
 *
 * @author CloudSen
 * @since 2019-08-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
