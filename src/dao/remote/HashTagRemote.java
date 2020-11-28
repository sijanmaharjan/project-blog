package dao.remote;

import javax.ejb.Remote;

@Remote
public interface HashTagRemote {
    Boolean checkIfExist(String tagName);
}
