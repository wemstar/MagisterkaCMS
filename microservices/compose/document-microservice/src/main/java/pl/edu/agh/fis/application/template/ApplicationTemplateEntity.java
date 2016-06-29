package pl.edu.agh.fis.application.template;

import pl.edu.agh.fis.activity.ActivityEntity;

import java.util.List;

/**
 * Created by wemstar on 2016-04-13.
 */
public class ApplicationTemplateEntity {
    public String id;
    public String title;
    public List<TemplateFieldsEntity> fields;
    public List<ActivityEntity> activities;
}
