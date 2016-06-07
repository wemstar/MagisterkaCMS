package pl.edu.agh.fis.rest.application.tempalte;

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
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.rest.interceptor.TokenInterceptor;
import pl.edu.agh.fis.rest.wraper.Resources;

/**
 * Created by wemstar on 2016-06-07.
 */
@Rest(rootUrl = Constraints.SERVER_ADRES,converters = { MappingJackson2HttpMessageConverter.class },interceptors = TokenInterceptor.class)
public interface ApplicationTemplateClient {

    @Get(value = "/document-server/template")
    @RequiresHeader("x-auth-token")
    Resources<DocumentDTO> getTemplates();

    @Post(value = "/document-server/template")
    @RequiresHeader("x-auth-token")
    void createTemplate(@Body ApplicationTemplateDTO document);

    @Put(value = "/document-server/template/{id}")
    @RequiresHeader("x-auth-token")
    void updateTamplate(@Path String id, @Body ApplicationTemplateDTO document);

    @Delete(value = "/document-server/template/{id}")
    @RequiresHeader("x-auth-token")
    void deleteTemplate(@Path String id);
}
