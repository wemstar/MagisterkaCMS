package pl.edu.agh.fis.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.fis.entity.user.UserEntity;
import pl.edu.agh.fis.service.user.UserRepository;


/**
 * Created by wemstar on 2016-04-02.
 */
@RepositoryRestController
public class UserRepositoryImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserEntity user) {

        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(),user.getLogin()));
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
        UserEntity repoUser = userRepository.findOne(id);
        user.setId(repoUser.getId());
        user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(),user.getLogin()));
        userRepository.save(user);
    }
}
