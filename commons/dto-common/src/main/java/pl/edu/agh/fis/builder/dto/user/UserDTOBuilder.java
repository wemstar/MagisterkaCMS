package pl.edu.agh.fis.builder.dto.user;

import pl.edu.agh.fis.dto.user.UserDTO;

/**
 * Created by wemstar on 2016-02-27.
 */
public class UserDTOBuilder {
    private Integer id;
    private String email;
    private String login;
    private String password;

    private UserDTOBuilder() {
    }

    public static UserDTOBuilder anUserDTO() {
        return new UserDTOBuilder();
    }

    public UserDTOBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserDTOBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder login(String login) {
        this.login = login;
        return this;
    }

    public UserDTOBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserDTOBuilder but() {
        return anUserDTO().id(id).email(email).login(login).password(password);
    }

    public UserDTO build() {
        UserDTO userDTO = new UserDTO();
        //userDTO.id = id;
        userDTO.email = email;
        userDTO.login = login;
        userDTO.password = password;
        return userDTO;
    }
}
