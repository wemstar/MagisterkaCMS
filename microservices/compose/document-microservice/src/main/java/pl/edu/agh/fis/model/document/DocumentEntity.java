package pl.edu.agh.fis.model.document;

import pl.edu.agh.fis.model.activity.ActivityEntity;
import pl.edu.agh.fis.model.commons.OwnedEntity;
import pl.edu.agh.fis.model.verification.VerificationStep;

import java.util.Date;
import java.util.List;

/**
 * Created by wemstar on 2016-04-13.
 */
public class DocumentEntity extends OwnedEntity{

    public String id;
    public String title;
    public Date date;
    public List<ChapterEntity> chapters;
    public List<ActivityEntity> activities;
    public List<VerificationStep> verificationSteps;
    public List<Long> allowedUserGroups;
}
