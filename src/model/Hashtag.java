package model;

import javax.persistence.*;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "tag.count", query = "SELECT COUNT(t) FROM Hashtag t WHERE t.title=:title"),
        @NamedQuery(name = "tag.findByTitle", query = "SELECT t FROM Hashtag t WHERE t.title=:title"),
        @NamedQuery(name = "tag.list", query = "SELECT t FROM Hashtag t, TaggedBlog b WHERE b.hashtag.id = t.id AND b.blog.id = :blogId")
})
@NamedNativeQueries(
        @NamedNativeQuery(name = "tag.empty.delete", query = "DELETE FROM HASHTAG h WHERE h.id IN (SELECT DISTINCT(t.id) FROM HASHTAG t LEFT JOIN TAGGEDBLOG b ON b.tag_id = t.id WHERE b.id IS NULL)")
)
public class Hashtag implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20, unique = true, nullable = false)
    private String title;

    public Hashtag() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
