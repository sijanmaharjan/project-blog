package dao.remote;

import model.Blog;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BlogRemote {
    Blog addNewBlog(Blog blog, String ... tags);
    void updateBlog(Blog blog);
    void deleteBlog(String id);
    void addLike(String blogId, String viewerId);
    void removeLike(String blogId, String viewerId);
    Blog getBlog(String id, boolean view);
    List<Blog> getList(int offset);
    List<Blog> getRelatedBlogs(String blogId, String ... tags);
    List<Blog> getRelatedBlogs(String blogId, int offset, String ... tags);
    List<Blog> getRandomBlogs();
    List<Blog> getRandomBlogs(int offset);
    List<Blog> getPopularBlogs();
    List<Blog> getHighlyLikedBlogs();
    List<Blog> searchBlogs(String text, int offset);
    List<Blog> filter(int offset, String ... tags);
    Long getLikeCount(String blogId);
    boolean checkLikes(String blogId, String viewerId);
}
