package pl.edu.agh.fis.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

/**
 * Created by wemstar on 2016-02-27.
 */
@JsonIgnoreProperties({"_links"})
public class UserGroupDTO {

    private Long id;
    private Set<UserDTO> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}
