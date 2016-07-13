package pl.edu.agh.fis.utils;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.dto.user.UserDTO;
import pl.edu.agh.fis.dto.user.UserGroupDTO;
import pl.edu.agh.fis.rest.user.UserCRUDClient;

/**
 * Created by wemstar on 2016-07-06.
 */
@EBean(scope = EBean.Scope.Singleton)
public class UserContext {

    @RestService
    UserCRUDClient userClient;

    private UserDTO userContext;
    private List<UserGroupDTO> userGroups;

    @Background
    public void reloadUser() {
        userContext = userClient.getCurrentUser();
        userGroups = new ArrayList<>(userClient.getUserGroup(userContext.id).getContent());
    }

    public UserDTO getUserContext() {
        return userContext;
    }

    public List<UserGroupDTO> getUserGroups() {
        return userGroups;
    }

    public List<Long> getUserGroupsIds() {
        List<Long> userGroupsIds = new ArrayList<>();
        for (UserGroupDTO userGroupDTO : getUserGroups()) {
            userGroupsIds.add(userGroupDTO.getId());
        }
        return userGroupsIds;
    }
}
