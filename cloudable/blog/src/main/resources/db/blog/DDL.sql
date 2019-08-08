SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `cloudable_blog`.`t_article`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'UUID',
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `summary` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '概述',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '正文',
  `img_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '预览大图片',
  `img_url_md` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '预览中等图片',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，1已删除，0未删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章表' ROW_FORMAT = Dynamic;

CREATE TABLE `cloudable_blog`.`t_article_tag`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'UUID',
  `article_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章UUID',
  `tag_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章标签UUID',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，1删除，0未删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章-标签多对多关系表' ROW_FORMAT = Dynamic;

CREATE TABLE `cloudable_blog`.`t_comment`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'UUID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论人',
  `user_url` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '个人网站',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '评论内容，可以是MD格式',
  `article_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章UUID',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，1已删除，0未删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章评论表' ROW_FORMAT = Dynamic;

CREATE TABLE `cloudable_blog`.`t_tag`  (
  `uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'UUID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `color` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'primary' COMMENT '标签颜色class',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除，1已删除，0未删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '文章标签表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS=1;