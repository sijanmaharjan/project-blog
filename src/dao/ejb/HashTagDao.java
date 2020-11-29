package dao.ejb;

import dao.remote.HashTagRemote;
import model.EntityMan;

import javax.ejb.Stateless;

@Stateless
public class HashTagDao implements HashTagRemote {
    @Override
    public boolean checkIfExist(String tagName) {
        return (EntityMan.execute(em->em.createNamedQuery("tag.exists", Long.class)
                .setParameter("title", tagName)
                .getSingleResult())) > 0;
    }
}
