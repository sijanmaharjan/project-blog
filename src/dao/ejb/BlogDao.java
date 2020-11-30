package dao.ejb;

import dao.remote.BlogRemote;
import model.*;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class BlogDao implements BlogRemote {

    final static public int LIST_LIMIT = 15;
    final static public int SUGGEST_LIMIT = 5;
    final static public int DEFAULT_OFFSET = 0;

    @Override
    public Blog addNewBlog(Blog blog, String ... tags) {
        blog.setId(UUID.randomUUID().toString());
        EntityMan.execTransaction(em->{
            em.persist(blog);
            if(tags != null){
                HashTagDao hashTagDao = new HashTagDao();
                for(String tag: tags){
                    if(!tag.trim().isEmpty()) {
                        Hashtag hashtag = hashTagDao.getOrCreate(tag.trim());
                        TaggedBlog taggedBlog = new TaggedBlog();
                        taggedBlog.setBlog(blog);
                        taggedBlog.setHashtag(hashtag);
                        em.persist(taggedBlog);
                    }
                }
            }
        });
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
            em.createNamedQuery("like.deleteByBlogId").setParameter("blogId", id).executeUpdate();
            em.createNamedQuery("blog.deleteById").setParameter("id", id).executeUpdate();
            List<Integer> hashtags = em.createNamedQuery("tag.empty.delete", Integer.class).getResultList();
            hashtags.forEach(tag->em.createQuery("DELETE FROM Hashtag h where h.id=:id").setParameter("id", tag).executeUpdate());
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
        return EntityMan.execute(em->em.createNativeQuery("SELECT b.* FROM BLOG b ORDER BY b.TIMESTAMP DESC LIMIT "+LIST_LIMIT+" OFFSET "+offset, Blog.class).getResultList());
    }

    @Override
    public List<Hashtag> getTags(String blogId) {
        return EntityMan.execute(em->em.createNamedQuery("tag.list", Hashtag.class).setParameter("blogId", blogId).getResultList());
    }

    @Override
    public List<Blog> getRelatedBlogs(String blogId, List<String> tags) {
        if(!tags.isEmpty()) return getFiltered(SUGGEST_LIMIT, DEFAULT_OFFSET, tags).stream().filter(blog -> !blog.getId().equals(blogId)).collect(Collectors.toList());
        else return new ArrayList<>();
    }

    @Override
    public List<Blog> getRelatedBlogs(String blogId, int offset, List<String> tags) {
        if(!tags.isEmpty()) return filter(offset, tags).stream().filter(blog -> !blog.getId().equals(blogId)).collect(Collectors.toList());
        else return new ArrayList<>();
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
        return EntityMan.execute(em->em.createNativeQuery("SELECT b.* FROM BLOG b WHERE b.view_count > 0 ORDER BY b.view_count DESC LIMIT "+SUGGEST_LIMIT, Blog.class)
                .getResultList());
    }

    @Override
    public List<Blog> getHighlyLikedBlogs() {
        return EntityMan.execute(em->em.createNativeQuery(
                "SELECT b.* " +
                        "FROM BLOG b, (" +
                        "SELECT l.blog_id, count(l.id) as total " +
                        "FROM LIKEDBLOG l " +
                        "GROUP BY l.blog_id) as likes " +
                        "WHERE likes.blog_id = b.id AND likes.total > 0 " +
                        "ORDER BY likes.total DESC " +
                        "LIMIT "+SUGGEST_LIMIT, Blog.class)
                .getResultList());
    }

    @Override
    public List<Blog> searchBlogs(String text, int offset) {
        return EntityMan.execute(em->em.createNativeQuery(
                    "SELECT b.* " +
                        "FROM BLOG b " +
                        "WHERE b.TITLE LIKE '%"+text+"%' OR b.sub_title LIKE '%"+text+"%' " +
                        "ORDER BY b.TIMESTAMP DESC " +
                        "LIMIT "+LIST_LIMIT+" " +
                        "OFFSET "+offset, Blog.class)
                .getResultList());
    }

    @Override
    public List<Blog> filter(int offset, List<String> tags) {
        if(!tags.isEmpty()) return getFiltered(LIST_LIMIT, offset, tags);
        else return getList(offset);
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

    private List<Blog> getFiltered(int limit, int offset, List<String> tags){
        return EntityMan.execute(em->em.createNativeQuery(
                    "SELECT b.* " +
                        "FROM BLOG b " +
                        "WHERE b.ID IN (" +
                        "SELECT t.blog_id " +
                        "from TAGGEDBLOG t, HASHTAG h " +
                        "WHERE t.tag_id = h.ID " +
                        "AND h.TITLE IN "+ Arrays.toString(tags.stream().map(tag->"'"+tag+"'").toArray())
                                                .replace("[", "(")
                                                .replace("]", ")") +") " +
                        "ORDER BY RAND() " +
                        "LIMIT "+limit+" " +
                        "OFFSET "+offset, Blog.class)
                .getResultList());
    }

    private List<Blog> getRandom(int limit, int offset){
        return EntityMan.execute(em->em.createNativeQuery(
                    "SELECT b.* " +
                        "FROM BLOG b " +
                        "ORDER BY RAND() " +
                        "LIMIT "+limit+" " +
                        "OFFSET "+offset, Blog.class)
                .getResultList());
    }
}
