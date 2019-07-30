package com.collapseunion.commonapi.cloudable.blog.entity;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

/**
 * 文章——标签 多对多实体类
 *
 * @author CloudSen
 */
@Entity
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "article_tag", schema = "public", catalog = "cloudsen_blog")
public class ArticleTagEntity {
    private String id;
    private Object createTime;
    private Object updateTime;
    private Boolean deleted;
    private ArticleEntity articleByArticleId;
    private TagEntity tagByTagId;

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

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    public ArticleEntity getArticleByArticleId() {
        return articleByArticleId;
    }

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false)
    public TagEntity getTagByTagId() {
        return tagByTagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArticleTagEntity that = (ArticleTagEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
