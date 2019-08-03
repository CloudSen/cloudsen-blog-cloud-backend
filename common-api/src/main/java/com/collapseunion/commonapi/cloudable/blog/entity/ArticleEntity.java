package com.collapseunion.commonapi.cloudable.blog.entity;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * 博客文章表
 *
 * @author CloudSen
 */
@Entity
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "article", schema = "cloudable", catalog = "cloudsen_blog")
public class ArticleEntity {
    private String id;
    private Object createTime;
    private Object updateTime;
    private Boolean deleted;
    private String title;
    private String summary;
    private String imgUrl;
    private String imgUrlMd;
    private String content;
    private Collection<ArticleTagEntity> articleAndTagById;
    private Collection<CommentEntity> commentsById;

    @Id
    @Column(name = "id", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Object getCreateTime() {
        return createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    public Object getUpdateTime() {
        return updateTime;
    }

    @Basic
    @Column(name = "deleted", nullable = false)
    public Boolean getDeleted() {
        return deleted;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 150)
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "summary", nullable = false, length = 300)
    public String getSummary() {
        return summary;
    }

    @Basic
    @Column(name = "img_url", nullable = false, length = 300)
    public String getImgUrl() {
        return imgUrl;
    }

    @Basic
    @Column(name = "img_url_md", nullable = false, length = 300)
    public String getImgUrlMd() {
        return imgUrlMd;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    @OneToMany(mappedBy = "articleByArticleId")
    public Collection<ArticleTagEntity> getArticleAndTagById() {
        return articleAndTagById;
    }

    @OneToMany(mappedBy = "articleByArticleId")
    public Collection<CommentEntity> getCommentsById() {
        return commentsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArticleEntity that = (ArticleEntity) o;
        return id.equals(that.id) &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
