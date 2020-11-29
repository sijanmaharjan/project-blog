package dao.remote;

import model.Profile;

import javax.ejb.Remote;

@Remote
public interface ProfileRemote {
    Profile saveProfile(Profile profile);
    void updateProfile(Profile profile);
    Profile getProfile();
    boolean checkIfProfileExists();
}
