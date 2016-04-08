package pl.edu.agh.fis.utils;

import org.androidannotations.annotations.EBean;

/**
 * Created by wemstar on 2016-04-07.
 */
@EBean(scope = EBean.Scope.Singleton)
public class TokenKeeper {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
