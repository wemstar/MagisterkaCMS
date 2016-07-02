package pl.edu.agh.fis.application;

import org.springframework.data.mongodb.core.mapping.DBRef;
import pl.edu.agh.fis.activity.ActivityEntity;

import java.util.List;

/**
 * Created by wemstar on 2016-06-06.
 */
public class ApplicationEntity {

    public String id;
    public String templateId;
    public String title;
    public List<FieldEntity> fields;
    public List<ActivityEntity> activities;
    public Long author;
}
