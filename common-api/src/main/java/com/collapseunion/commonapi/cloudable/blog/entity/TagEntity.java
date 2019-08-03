package com.collapseunion.commonapi.cloudable.blog.entity;

import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * 文章标签表
 *
 * @author CloudSen
 */
@Entity
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "tag", schema = "cloudable", catalog = "cloudsen_blog")
public class TagEntity {
    private String id;
    private Object createTime;
    private Object updateTime;
    private Boolean deleted;
    private String name;
    private String color;
    private Collection<ArticleTagEntity> articleAndTagById;

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
    @Column(name = "name", nullable = false, length = 36)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "color", nullable = false, length = 30)
    public String getColor() {
        return color;
    }

    @OneToMany(mappedBy = "tagByTagId")
    public Collection<ArticleTagEntity> getArticleAndTagById() {
        return articleAndTagById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagEntity tagEntity = (TagEntity) o;
        return id.equals(tagEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
