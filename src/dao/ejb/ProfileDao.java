package dao.ejb;

import dao.remote.ProfileRemote;
import model.EntityMan;
import model.Profile;

import javax.ejb.Stateless;

@Stateless
public class ProfileDao implements ProfileRemote {
    @Override
    public Profile saveProfile(Profile profile) {
        EntityMan.execTransaction(em->em.persist(profile));
        return getProfile();
    }

    @Override
    public void updateProfile(Profile profile) {
        EntityMan.execTransaction(em->em.merge(profile));
    }

    @Override
    public Profile getProfile() {
        return EntityMan.execute(em->em.createNamedQuery("profile.get", Profile.class).getSingleResult());
    }

    @Override
    public boolean checkIfProfileExists() {
        return (EntityMan.execute(em->em.createNamedQuery("profile.count", Long.class)
                .getSingleResult())) > 0;
    }
}
