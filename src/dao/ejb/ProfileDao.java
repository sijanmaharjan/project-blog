package dao.ejb;

import dao.remote.ProfileRemote;
import model.Profile;

import javax.ejb.Stateless;

@Stateless
public class ProfileDao implements ProfileRemote {
    @Override
    public void saveProfile(Profile profile) {

    }

    @Override
    public void updateProfile(Profile profile) {

    }

    @Override
    public Profile getProfile() {
        return null;
    }
}
