package pl.edu.agh.fis.builder.dto.application;

import java.util.List;

import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.FieldDTO;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;

/**
 * Created by wemstar on 2016-06-14.
 */
public class ApplicationDTOBuilder {

    private String id;
    private String title;
    private String templateId;
    private List<FieldDTO> fields;
    private List<ActivityDTO> activities;
    private List<VerificationStepDTO> verificationSteps;
    private List<Long> allowedUserGroups;

    private ApplicationDTOBuilder() {
    }

    public static ApplicationDTOBuilder anApplicationTemplateDTO() {
        return new ApplicationDTOBuilder();
    }

    public ApplicationDTOBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ApplicationDTOBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ApplicationDTOBuilder templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public ApplicationDTOBuilder activities(List<ActivityDTO> activities) {
        this.activities = activities;
        return this;
    }

    public ApplicationDTOBuilder fields(List<FieldDTO> fields) {
        this.fields = fields;
        return this;
    }

    public ApplicationDTOBuilder verificationSteps(List<VerificationStepDTO> verificationSteps) {
        this.verificationSteps = verificationSteps;
        return this;
    }

    public ApplicationDTOBuilder allowedUserGroups(List<Long> allowedUserGroups) {
        this.allowedUserGroups = allowedUserGroups;
        return this;
    }


    public ApplicationDTO build() {
        ApplicationDTO templateDTO = new ApplicationDTO();
        templateDTO.id = id;
        templateDTO.title = title;
        templateDTO.templateId = templateId;
        templateDTO.fields = fields;
        templateDTO.activities = activities;
        templateDTO.allowedUserGroups = allowedUserGroups;
        templateDTO.verificationSteps = verificationSteps;
        return templateDTO;
    }
}
