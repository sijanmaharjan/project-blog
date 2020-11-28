package dao.remote;

import model.Profile;

import javax.ejb.Remote;

@Remote
public interface ProfileRemote {
    void saveProfile(Profile profile);
    void updateProfile(Profile profile);
    Profile getProfile();
}
