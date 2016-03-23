package pl.edu.agh.fis.user.mange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.fis.clients.user.UserCore;
import pl.edu.agh.fis.dto.use.UserDTO;

/**
 * Created by wemstar on 2016-03-02.
 */

@RestController
@RequestMapping(path = "/user/manage")
public class UserManageController {

    @Autowired
    private UserCore userCore;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @RequestMapping(method = RequestMethod.POST)
    public void create(UserDTO user){
        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(),user.getLogin()));
        userCore.createUser(user);
    }

    public boolean checkUser(String login,String password){
        Resource<UserDTO> user = userCore.getUserByLogin(login);
        return shaPasswordEncoder.isPasswordValid(user.getContent().getPassword(),password,login);
    }
}
