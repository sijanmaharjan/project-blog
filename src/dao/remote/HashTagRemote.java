package dao.remote;

import javax.ejb.Remote;

@Remote
public interface HashTagRemote {
    boolean checkIfExist(String tagName);
}
