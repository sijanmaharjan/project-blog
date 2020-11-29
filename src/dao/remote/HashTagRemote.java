package dao.remote;

import model.Hashtag;

import javax.ejb.Remote;

@Remote
public interface HashTagRemote {
    boolean checkIfExist(String tagName);
    Hashtag getOrCreate(String tagName);
    Hashtag find(String tagName);
}
