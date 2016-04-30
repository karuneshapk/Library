package gl.mike.pojos;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by user on 17.04.2016.
 */
public class Status implements Serializable {
    private static final long serialVersionUTD = -9798687466565L;

    private Integer statusId;
    private String status;
    private Set<User> userLists;

    public Status() {

    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<User> getUserLists() {
        return userLists;
    }

    public void setUserLists(Set<User> userLists) {
        this.userLists = userLists;
    }
}
