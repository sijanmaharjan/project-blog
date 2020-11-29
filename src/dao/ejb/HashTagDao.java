package dao.ejb;

import dao.remote.HashTagRemote;
import model.EntityMan;
import model.Hashtag;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class HashTagDao implements HashTagRemote {
    @Override
    public boolean checkIfExist(String tagName) {
        return (EntityMan.execute(em->em.createNamedQuery("tag.count", Long.class)
                .setParameter("title", tagName)
                .getSingleResult())) > 0;
    }

    @Override
    public Hashtag getOrCreate(String tagName) {
        Hashtag hashtag= find(tagName);
        if(hashtag == null){
            Hashtag tag = new Hashtag();
            tag.setTitle(tagName);
            EntityMan.execTransaction(em->em.persist(tag));
            hashtag = tag;
        }
        return hashtag;
    }

    public Hashtag find(String tagName) {
        try {
            return EntityMan.execute(em -> em.createNamedQuery("tag.findByTitle", Hashtag.class).setParameter("title", tagName).getSingleResult());
        }catch (NoResultException e){
            return null;
        }
    }
}
