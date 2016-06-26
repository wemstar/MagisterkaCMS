package pl.edu.agh.fis.dto.activity;

import java.io.Serializable;

/**
 * Created by wemstar on 2016-06-20.
 */
public class ActivityDTO implements Serializable {

    public String id;
    public String userId;
    public ActionType actionType;
    public CommentDTO comment;
    //public ActionDTO action;
}
