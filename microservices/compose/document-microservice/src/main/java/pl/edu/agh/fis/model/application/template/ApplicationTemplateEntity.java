package pl.edu.agh.fis.model.application.template;

import pl.edu.agh.fis.model.activity.ActivityEntity;
import pl.edu.agh.fis.model.commons.OwnedEntity;
import pl.edu.agh.fis.model.verification.VerificationStep;

import java.util.List;

/**
 * Created by wemstar on 2016-04-13.
 */
public class ApplicationTemplateEntity extends OwnedEntity {
    public String id;
    public String title;
    public List<TemplateFieldsEntity> fields;
    public List<ActivityEntity> activities;
    public List<VerificationStep> verificationSteps;
    public List<VerificationStep> templateVerificationSteps;
    public List<Long> allowedUserGroups;
}
