package pl.edu.agh.fis.builder.dto.use;

import pl.edu.agh.fis.dto.use.UserDTO;

/**
 * Created by wemstar on 2016-02-27.
 */
public class UserDTOBuilder {
    private Long id;
    private String email;
    private String login;
    private String password;

    private UserDTOBuilder() {
    }

    public static UserDTOBuilder anUserDTO() {
        return new UserDTOBuilder();
    }

    public UserDTOBuilder id(Long id) {
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
        userDTO.setId(id);
        userDTO.setEmail(email);
        userDTO.setLogin(login);
        userDTO.setPassword(password);
        return userDTO;
    }
}
