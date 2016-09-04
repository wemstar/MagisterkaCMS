package pl.edu.agh.fis.rest.document;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import pl.edu.agh.fis.Constraints;
import pl.edu.agh.fis.rest.interceptor.TokenInterceptor;

/**
 * Created by wemstar on 2016-09-04.
 */
@Rest(rootUrl = Constraints.SERVER_ADRES,converters = { MappingJackson2HttpMessageConverter.class },interceptors = TokenInterceptor.class)
public interface DocumentPDFClient {

    @Get(value = "/document-pdf-microservice/pdf/generation/document/{id}")
    @RequiresHeader("x-auth-token")
    String[] generateDocumentPDF(@Path String id);

    @Get(value = "/document-pdf-microservice/pdf/generation/application/{id}")
    @RequiresHeader("x-auth-token")
    String generateApplicationPDF(@Path String id);
}
