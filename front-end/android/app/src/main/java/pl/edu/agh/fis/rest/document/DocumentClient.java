package pl.edu.agh.fis.rest.document;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import pl.edu.agh.fis.Constraints;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.rest.interceptor.TokenInterceptor;
import pl.edu.agh.fis.rest.wraper.Resources;


/**
 * Created by wemstar on 2016-05-07.
 */
@Rest(rootUrl = Constraints.SERVER_ADRES,converters = { MappingJackson2HttpMessageConverter.class },interceptors = TokenInterceptor.class)
public interface DocumentClient extends RestClientHeaders {

    @Get(value = "/document-server/document")
    @RequiresHeader("x-auth-token")
    Resources<DocumentDTO> getDocuments();

    @Post(value = "/document-server/document")
    @RequiresHeader("x-auth-token")
    void createDocument(@Body DocumentDTO document);
}
