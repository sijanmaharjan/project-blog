package model;

import javax.persistence.*;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "tag.exists", query = "SELECT COUNT(t) FROM Hashtag t WHERE t.title=:title")
})
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
