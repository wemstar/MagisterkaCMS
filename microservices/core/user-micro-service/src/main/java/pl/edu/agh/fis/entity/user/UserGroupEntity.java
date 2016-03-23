package pl.edu.agh.fis.entity.user;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by wemstar on 2016-02-27.
 */
@Entity
@Table(name = "USER_GROUP_ENTITY")
public class UserGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_GROUP_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_GROUP_USER", joinColumns = { @JoinColumn(name = "USER_GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
    private Set<UserEntity> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
