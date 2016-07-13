package pl.edu.agh.fis.model.application;

import pl.edu.agh.fis.model.activity.ActivityEntity;
import pl.edu.agh.fis.model.commons.OwnedEntity;
import pl.edu.agh.fis.model.verification.VerificationStep;

import java.util.List;

/**
 * Created by wemstar on 2016-06-06.
 */
public class ApplicationEntity extends OwnedEntity {

    public String id;
    public String templateId;
    public String title;
    public List<FieldEntity> fields;
    public List<ActivityEntity> activities;
    public List<VerificationStep> verificationSteps;
    public List<Long> allowedUserGroups;
}
