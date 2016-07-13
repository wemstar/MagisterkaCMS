package pl.edu.agh.fis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.fis.model.activity.ActionType;
import pl.edu.agh.fis.model.activity.ActivityEntity;
import pl.edu.agh.fis.model.activity.CommentEntity;
import pl.edu.agh.fis.dto.user.UserDTO;

/**
 * Created by wemstar on 2016-07-03.
 */
@Component
public class CommentUtils {

    @Autowired
    UserCommonUtils userCommonUtils;

    public ActivityEntity createChangeAction() {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.actionType = ActionType.CHANGE_CONTENT;
        UserDTO user = userCommonUtils.getCurrentUser();
        activityEntity.userId = user.id;
        activityEntity.comment = new CommentEntity(user.id,"Changed by " + user.login);
        return activityEntity;
    }
}
