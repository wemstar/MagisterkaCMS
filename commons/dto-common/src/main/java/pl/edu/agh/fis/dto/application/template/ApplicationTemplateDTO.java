package pl.edu.agh.fis.dto.application.template;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;

/**
 * Created by wemstar on 2016-04-13.
 */
@JsonIgnoreProperties({"_links"})
public class ApplicationTemplateDTO implements Serializable {
    public String id;
    public String title;
    public List<TemplateFieldsDTO> fields;
    public List<ActivityDTO> activities;
    public Long author;
    public List<VerificationStepDTO> verificationSteps;
    public List<VerificationStepDTO> templateVerificationSteps;
    public List<Long> allowedUserGroups;
}
