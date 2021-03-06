package pl.edu.agh.fis.entity.user;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by wemstar on 2016-02-23.
 */
@Entity
@Table(name = "USER_ENTITY")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_EMAIL",nullable = false,unique = true)
    private String email;
    @Column(name = "USER_LOGIN",nullable = false,unique = true)
    private String login;
    @Column(name = "USER_PASSWORD")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<UserGroupEntity> userGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<UserGroupEntity> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroupEntity> userGroups) {
        this.userGroups = userGroups;
    }
}
