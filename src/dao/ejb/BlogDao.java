package dao.ejb;

import dao.remote.BlogRemote;
import model.Blog;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BlogDao implements BlogRemote {
    @Override
    public void addNewBlog(Blog blog) {

    }

    @Override
    public void updateBlog(Blog blog) {

    }

    @Override
    public void deleteBlog(int id) {

    }

    @Override
    public void addLike(int blogId, String viewerId) {

    }

    @Override
    public void removeLike(int blogId, String viewerId) {

    }

    @Override
    public Blog getBlog(int id) {
        return null;
    }

    @Override
    public List<Blog> getList(int offset) {
        return null;
    }

    @Override
    public List<Blog> getRelatedBlogs(int... tagIds) {
        return null;
    }

    @Override
    public List<Blog> getRelatedBlogs(int offset, int... tagIds) {
        return null;
    }

    @Override
    public List<Blog> getRandomBlogs() {
        return null;
    }

    @Override
    public List<Blog> getRandomBlogs(int offset) {
        return null;
    }

    @Override
    public List<Blog> getPopularBlogs() {
        return null;
    }

    @Override
    public List<Blog> getHighlyLikedBlogs() {
        return null;
    }

    @Override
    public List<Blog> searchBlogs(String text) {
        return null;
    }

    @Override
    public List<Blog> filter(int... tagIds) {
        return null;
    }

    @Override
    public Integer getViewCount(int blogId) {
        return null;
    }

    @Override
    public Integer getLikeCount(int blogId) {
        return null;
    }

    @Override
    public Boolean checkLikes(int blogId, String viewerId) {
        return null;
    }
}
