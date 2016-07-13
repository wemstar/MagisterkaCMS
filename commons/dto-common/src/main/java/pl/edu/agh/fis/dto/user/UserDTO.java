package pl.edu.agh.fis.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by wemstar on 2016-02-27.
 */
@JsonIgnoreProperties({"_links"})
public class UserDTO {

    public Long id;
    public String email;
    public String login;
    public String password;

}
