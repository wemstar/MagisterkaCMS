package pl.edu.agh.fis.rest.application;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Delete;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Put;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import pl.edu.agh.fis.Constraints;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.rest.interceptor.TokenInterceptor;
import pl.edu.agh.fis.rest.wraper.Resources;

/**
 * Created by wemstar on 2016-06-14.
 */
@Rest(rootUrl = Constraints.SERVER_ADRES, converters = {MappingJackson2HttpMessageConverter.class}, interceptors = TokenInterceptor.class)
public interface ApplicationClient {

    @Get(value = "/document-server/application")
    @RequiresHeader("x-auth-token")
    Resources<ApplicationDTO> getApplications();

    @Post(value = "/document-server/application")
    @RequiresHeader("x-auth-token")
    void createApplication(@Body ApplicationDTO application);

    @Put(value = "/document-server/application/{id}")
    @RequiresHeader("x-auth-token")
    void updateApplication(@Path String id, @Body ApplicationDTO application);

    @Delete(value = "/document-server/application/{id}")
    @RequiresHeader("x-auth-token")
    void deleteApplication(@Path String id);
}
