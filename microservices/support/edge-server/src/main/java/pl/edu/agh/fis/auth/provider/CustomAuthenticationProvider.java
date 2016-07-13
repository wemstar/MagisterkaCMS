package pl.edu.agh.fis.auth.provider;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import pl.edu.agh.fis.clients.user.UserCore;
import pl.edu.agh.fis.dto.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wemstar on 2016-04-08.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserCore userCore;

    @Autowired
    ShaPasswordEncoder shaPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // user the credentials to try to authenticate against the third party system
        if (authenticatedAgainstThirdPartySystem(name,password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            return new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
        } else {
            throw new BadCredentialsException("Unable to auth against third party systems");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public boolean authenticatedAgainstThirdPartySystem(String name,String password) {
        UserDTO userDTO = userCore.getUserByLogin(name);
        return shaPasswordEncoder.isPasswordValid(userDTO.password,password,name);
    }
}
