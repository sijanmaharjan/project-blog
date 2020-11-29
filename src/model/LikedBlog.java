package model;

import javax.persistence.*;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "like.deleteByBlogId", query = "DELETE FROM LikedBlog b WHERE b.blog.id = :blogId"),
        @NamedQuery(name = "like.remove", query = "DELETE FROM LikedBlog b WHERE b.blog.id = :blogId AND b.viewerId = :viewerId")
})
public class LikedBlog implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;
    @Column(name= "viewer_id", length = 64, nullable = false)
    private String viewerId;

    public LikedBlog() {
    }

    public LikedBlog(Blog blog, String viewerId) {
        this.blog = blog;
        this.viewerId = viewerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getViewerId() {
        return viewerId;
    }

    public void setViewerId(String viewerId) {
        this.viewerId = viewerId;
    }
}
