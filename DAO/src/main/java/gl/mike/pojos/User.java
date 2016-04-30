package gl.mike.pojos;

import java.io.Serializable;

/**
 * Created by user on 17.04.2016.
 */
public class User implements Serializable {
    private static final long serialVersionUTD = -979868766565L;

    private Integer userId;
    private String login;
    private String password;
    private Status statusName;

    public User() {

    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatusName() {
        return statusName;
    }

    public void setStatusName(Status statusName) {
        this.statusName = statusName;
    }
}
