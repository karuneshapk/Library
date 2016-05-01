package gl.mike.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by user on 17.04.2016.
 */
@Entity
@Table(name = "STATUSES")
public class Status implements Serializable {
    private static final long serialVersionUTD = -9798687466565L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID")
    private Integer statusId;

    @Column(name = "STATUS")
    private String status;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="statusName")
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
