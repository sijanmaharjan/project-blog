package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "blog.like.count", query = "SELECT count(l) from LikedBlog l where l.blog.id=:blogId"),
        @NamedQuery(name = "blog.viewer.like", query = "SELECT count(l) from LikedBlog l where l.blog.id=:blogId AND l.viewerId=:viewerId"),
        @NamedQuery(name = "blog.deleteById", query = "DELETE FROM Blog b WHERE b.id = :id")
})
public class Blog implements java.io.Serializable {
    @Id
    @Column(length = 64)
    private String id;
    @Column(name = "cover_image", nullable = false)
    private String coverImage;
    @Column(length = 125, nullable = false)
    private String title;
    @Column(name = "sub_title", length = 170)
    private String subTitle;
    @Column(name = "content", length = 5000, nullable = false)
    private String content;
    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp = new Date();

    public Blog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
