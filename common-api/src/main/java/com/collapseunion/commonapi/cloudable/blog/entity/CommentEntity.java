package com.collapseunion.commonapi.cloudable.blog.entity;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

/**
 * 文章评论实体类
 *
 * @author CloudSen
 */
@Entity
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "comment", schema = "public", catalog = "cloudsen_blog")
public class CommentEntity {
    private String id;
    private String userName;
    private String userUrl;
    private String userAvatarId;
    private String content;
    private Object createTime;
    private Object updateTime;
    private Boolean deleted;
    private ArticleEntity articleByArticleId;

    @Id
    @Column(name = "id", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 30)
    public String getUserName() {
        return userName;
    }

    @Basic
    @Column(name = "user_url", nullable = false, length = 100)
    public String getUserUrl() {
        return userUrl;
    }

    @Basic
    @Column(name = "user_avatar_id", nullable = false, length = 36)
    public String getUserAvatarId() {
        return userAvatarId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
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

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    public ArticleEntity getArticleByArticleId() {
        return articleByArticleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommentEntity that = (CommentEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
