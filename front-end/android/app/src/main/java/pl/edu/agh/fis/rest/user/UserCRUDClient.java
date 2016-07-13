package pl.edu.agh.fis.rest.user;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import pl.edu.agh.fis.Constraints;
import pl.edu.agh.fis.dto.user.UserDTO;
import pl.edu.agh.fis.dto.user.UserGroupDTO;
import pl.edu.agh.fis.rest.interceptor.TokenInterceptor;
import pl.edu.agh.fis.rest.wraper.Resources;

/**
 * Created by wemstar on 2016-07-06.
 */
@Rest(rootUrl = Constraints.SERVER_ADRES, converters = {MappingJackson2HttpMessageConverter.class}, interceptors = TokenInterceptor.class)
public interface UserCRUDClient extends RestClientHeaders {

    @Get(value = "/user-micro-service/user/current")
    UserDTO getCurrentUser();


    @Get(value = "/user-micro-service/user/{id}/userGroups")
    Resources<UserGroupDTO> getUserGroup(@Path Long id);
}
