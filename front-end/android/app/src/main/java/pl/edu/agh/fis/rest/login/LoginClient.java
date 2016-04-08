package pl.edu.agh.fis.rest.login;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.RequiresAuthentication;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import pl.edu.agh.fis.Constraints;

/**
 * Created by wemstar on 2016-04-06.
 */
@Rest(rootUrl = Constraints.SERVER_ADRES, converters = { MappingJackson2HttpMessageConverter.class })
public interface LoginClient extends RestClientHeaders {

    @Get("/")
    @RequiresAuthentication
    ResponseEntity<Void> login();
}
