package pl.edu.agh.fis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.fis.clients.edge.SessionClients;
import pl.edu.agh.fis.clients.user.UserCore;
import pl.edu.agh.fis.dto.user.UserDTO;

/**
 * Created by wemstar on 2016-07-02.
 */
@Service
public class UserCommonUtils {
    @Autowired
    SessionClients sessionClients;

    @Autowired
    UserCore userCore;


    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public UserDTO getCurrentUser() {
        String login = sessionClients.getCurrentUser();
        return userCore.getUserByLogin(login);
    }
}
