package pl.edu.agh.fis.rest.user.group;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Delete;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import pl.edu.agh.fis.Constraints;
import pl.edu.agh.fis.dto.user.UserGroupDTO;
import pl.edu.agh.fis.rest.interceptor.TokenInterceptor;
import pl.edu.agh.fis.rest.wraper.Resources;

/**
 * Created by wemstar on 2016-07-05.
 */
@Rest(rootUrl = Constraints.SERVER_ADRES, converters = {MappingJackson2HttpMessageConverter.class}, interceptors = TokenInterceptor.class)
public interface UserGroupClient extends RestClientHeaders {

    @Get(value = "/user-micro-service/group")
    @RequiresHeader("x-auth-token")
    Resources<UserGroupDTO> getUserGroups();

    @Post(value = "/user-micro-service/group")
    @RequiresHeader("x-auth-token")
    void createUserGroup(@Body UserGroupDTO userGroup);

    @Put(value = "/user-micro-service/group/{id}")
    @RequiresHeader("x-auth-token")
    void updateUserGroup(@Path String id, @Body UserGroupDTO userGroup);

    @Delete(value = "/user-micro-service/group/{id}")
    @RequiresHeader("x-auth-token")
    void deleteUserGroup(@Path String id);
}
