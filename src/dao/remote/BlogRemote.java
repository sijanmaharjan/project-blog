package dao.remote;

import model.Blog;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface BlogRemote {
    void addNewBlog(Blog blog);
    void updateBlog(Blog blog);
    void deleteBlog(int id);
    void addLike(int blogId, String viewerId);
    void removeLike(int blogId, String viewerId);
    Blog getBlog(int id);
    List<Blog> getList(int offset);
    List<Blog> getRelatedBlogs(int ... tagIds);
    List<Blog> getRelatedBlogs(int offset, int ... tagIds);
    List<Blog> getRandomBlogs();
    List<Blog> getRandomBlogs(int offset);
    List<Blog> getPopularBlogs();
    List<Blog> getHighlyLikedBlogs();
    List<Blog> searchBlogs(String text);
    List<Blog> filter(int ... tagIds);
    Integer getViewCount(int blogId);
    Integer getLikeCount(int blogId);
    Boolean checkLikes(int blogId, String viewerId);
}
