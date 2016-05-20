package pl.edu.agh.fis.rest.interceptor;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import pl.edu.agh.fis.utils.TokenKeeper;

/**
 * Created by wemstar on 2016-05-07.
 */
@EBean(scope = EBean.Scope.Singleton)
public class TokenInterceptor implements ClientHttpRequestInterceptor {

    @Bean
    TokenKeeper tokenKeeper;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("x-auth-token",tokenKeeper.getToken());
        //request.getHeaders().setAccept(Arrays.asList(new MediaType("application","x-spring-data-verbose+json")));
        return execution.execute(request, body);
    }
}
