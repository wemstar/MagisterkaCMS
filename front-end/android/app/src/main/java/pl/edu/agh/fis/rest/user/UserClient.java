package pl.edu.agh.fis.rest.user;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import pl.edu.agh.fis.Constraints;
import pl.edu.agh.fis.dto.user.UserDTO;

/**
 * Created by wemstar on 2016-04-02.
 */


@Rest(rootUrl = Constraints.SERVER_ADRES,converters = { MappingJackson2HttpMessageConverter.class })
public interface UserClient {


    @Post(value = "/user-micro-service/user")
    Object createUser(@Body  UserDTO user);
}
