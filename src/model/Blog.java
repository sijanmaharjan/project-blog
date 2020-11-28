package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "blog.view.count", query = "SELECT v.totalView from Blog b, ViewCount v where v.blogId=:blogId"),
        @NamedQuery(name = "blog.like.count", query = "SELECT count(l) from LikedBlog l where l.blog.id=:blogId"),
        @NamedQuery(name = "blog.viewer.like", query = "SELECT count(l) from LikedBlog l where l.viewerId=:viewerId")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "blog.search", query = "SELECT b.* FROM Blog b WHERE b.title LIKE %:text% OR b.sub_title LIKE %:text% ORDER BY b.timestamp DESC LIMIT :limit OFFSET :offset"),
        @NamedNativeQuery(name = "blog.list", query = "SELECT b.* FROM Blog b ORDER BY b.timestamp DESC LIMIT :limit OFFSET :offset"),
        @NamedNativeQuery(name = "blog.random", query = "SELECT b.* FROM Blog b ORDER BY RAND() LIMIT :limit OFFSET :offset"),
        @NamedNativeQuery(name = "blog.filter", query = "SELECT b.* FROM Blog b WHERE b.id IN (SELECT t.blog_id from TaggedBlog t WHERE t.tag_id IN :tagIds) LIMIT :limit OFFSET :offset"),
        @NamedNativeQuery(name = "blog.popular", query = "SELECT b.* FROM Blog b, ViewCount v WHERE v.blogId = b.id AND v.total_view > 0 ORDER BY v.total_view DESC LIMIT :limit"),
        @NamedNativeQuery(name = "blog.highly.liked", query = "SELECT b.* FROM Blog b, (SELECT l.blog_id, count(l.id) as total FROM LikedBlog l GROUP BY l.blog_id) as likes i WHERE i.blog_id = b.id AND i.total > 0 ORDER BY i.total DESC LIMIT :limit")
})
public class Blog implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cover_image", nullable = false)
    private String coverImage;
    @Column(length = 125, nullable = false)
    private String title;
    @Column(name = "sub_title", length = 170)
    private String subTitle;
    @Column(name = "content", length = 1000, nullable = false)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp = new Date();

    public Blog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
