package dao.ejb;

import dao.remote.HashTagRemote;

import javax.ejb.Stateless;

@Stateless
public class HashTagDao implements HashTagRemote {
    @Override
    public Boolean checkIfExist(String tagName) {
        return null;
    }
}
