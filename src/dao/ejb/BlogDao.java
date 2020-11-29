package dao.ejb;

import dao.remote.BlogRemote;
import model.Blog;
import model.EntityMan;
import model.LikedBlog;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Stateless
public class BlogDao implements BlogRemote {

    final static public int LIST_LIMIT = 10;
    final static public int SUGGEST_LIMIT = 5;
    final static public int DEFAULT_OFFSET = 0;

    @Override
    public Blog addNewBlog(Blog blog) {
        blog.setId(UUID.randomUUID().toString());
        EntityMan.execTransaction(em->em.persist(blog));
        return blog;
    }

    @Override
    public void updateBlog(Blog blog) {
        EntityMan.execTransaction(em->em.merge(blog));
    }

    @Override
    public void deleteBlog(String id) {
        EntityMan.execTransaction(em->{
            em.createNamedQuery("tag.deleteByBlogId").setParameter("blogId", id).executeUpdate();
            em.createNamedQuery("tag.empty.delete").executeUpdate();
            em.createNamedQuery("like.deleteByBlogId").setParameter("blogId", id).executeUpdate();
            em.createNamedQuery("blog.deleteById").setParameter("id", id).executeUpdate();
        });
    }

    @Override
    public void addLike(String blogId, String viewerId) {
        Blog blog = getBlog(blogId, false);
        if(blog != null) {
            EntityMan.execTransaction(em -> em.persist(new LikedBlog(blog, viewerId)));
        }
    }

    @Override
    public void removeLike(String blogId, String viewerId) {
        EntityMan.execTransaction(em->
                em.createNamedQuery("like.remove").setParameter("blogId", blogId).setParameter("viewerId", viewerId).executeUpdate());
    }

    @Override
    public Blog getBlog(String id, boolean view) {
        try {
            Blog blog = EntityMan.execute(em -> em.find(Blog.class, id));
            if(view) {
                blog.setViewCount(blog.getViewCount() + 1);
                updateBlog(blog);
            }
            return blog;
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Blog> getList(int offset) {
        return EntityMan.execute(em->em.createNamedQuery("blog.list", Blog.class).getResultList());
    }

    @Override
    public List<Blog> getRelatedBlogs(int... tagIds) {
        return getFiltered(SUGGEST_LIMIT, DEFAULT_OFFSET, tagIds);
    }

    @Override
    public List<Blog> getRelatedBlogs(int offset, int... tagIds) {
        return getFiltered(LIST_LIMIT, offset, tagIds);
    }

    @Override
    public List<Blog> getRandomBlogs() {
        return getRandom(SUGGEST_LIMIT, DEFAULT_OFFSET);
    }

    @Override
    public List<Blog> getRandomBlogs(int offset) {
        return getRandom(LIST_LIMIT, offset);
    }

    @Override
    public List<Blog> getPopularBlogs() {
        return EntityMan.execute(em->em.createNamedQuery("blog.popular", Blog.class)
                .setParameter("limit", SUGGEST_LIMIT)
                .getResultList());
    }

    @Override
    public List<Blog> getHighlyLikedBlogs() {
        return EntityMan.execute(em->em.createNamedQuery("blog.highly.liked", Blog.class)
                .setParameter("limit", SUGGEST_LIMIT)
                .getResultList());
    }

    @Override
    public List<Blog> searchBlogs(String text, int offset) {
        return EntityMan.execute(em->em.createNamedQuery("blog.search", Blog.class)
                .setParameter("text", text)
                .setParameter("limit", LIST_LIMIT)
                .setParameter("offset", offset)
                .getResultList());
    }

    @Override
    public List<Blog> filter(int offset, int... tagIds) {
        return getFiltered(LIST_LIMIT, offset, tagIds);
    }

    @Override
    public Long getLikeCount(String blogId) {
        return EntityMan.execute(em->em.createNamedQuery("blog.like.count", Long.class)
                .setParameter("blogId", blogId)
                .getSingleResult());
    }

    @Override
    public boolean checkLikes(String blogId, String viewerId) {
        return (EntityMan.execute(em->em.createNamedQuery("blog.viewer.like", Long.class)
                .setParameter("blogId", blogId)
                .setParameter("viewerId", viewerId)
                .getSingleResult())) > 0;
    }

    private List<Blog> getFiltered(int limit, int offset, int ... tagIds){
        return EntityMan.execute(em->em.createNamedQuery("blog.filter", Blog.class)
                .setParameter("tagIds", tagIds)
                .setParameter("limit", limit)
                .setParameter("offset", offset)
                .getResultList());
    }

    private List<Blog> getRandom(int limit, int offset){
        return EntityMan.execute(em->em.createNamedQuery("blog.random", Blog.class)
                .setParameter("limit", limit)
                .setParameter("offset", offset)
                .getResultList());
    }
}
