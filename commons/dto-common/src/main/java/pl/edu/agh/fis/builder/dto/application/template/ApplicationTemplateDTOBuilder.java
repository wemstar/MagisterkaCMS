package pl.edu.agh.fis.builder.dto.application.template;

import java.util.List;

import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;

/**
 * Created by wemstar on 2016-06-07.
 */
public class ApplicationTemplateDTOBuilder {

    private String id;
    private String title;
    private List<TemplateFieldsDTO> fields;
    private List<ActivityDTO> activities;
    private List<VerificationStepDTO> verificationSteps;
    private List<VerificationStepDTO> templateVerificationSteps;
    private List<Long> allowedUserGroups;

    private ApplicationTemplateDTOBuilder() {
    }

    public static ApplicationTemplateDTOBuilder anApplicationTemplateDTO() {
        return new ApplicationTemplateDTOBuilder();
    }

    public ApplicationTemplateDTOBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ApplicationTemplateDTOBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ApplicationTemplateDTOBuilder fields(List<TemplateFieldsDTO> fields) {
        this.fields = fields;
        return this;
    }

    public ApplicationTemplateDTOBuilder activities(List<ActivityDTO> activities) {
        this.activities = activities;
        return this;
    }

    public ApplicationTemplateDTOBuilder verificationSteps(List<VerificationStepDTO> verificationSteps) {
        this.verificationSteps = verificationSteps;
        return this;
    }

    public ApplicationTemplateDTOBuilder templateVerificationSteps(List<VerificationStepDTO> templateVerificationSteps) {
        this.templateVerificationSteps = templateVerificationSteps;
        return this;
    }

    public ApplicationTemplateDTOBuilder allowedUserGroups(List<Long> allowedUserGroups) {
        this.allowedUserGroups = allowedUserGroups;
        return this;
    }

    public ApplicationTemplateDTO build() {
        ApplicationTemplateDTO templateDTO = new ApplicationTemplateDTO();
        templateDTO.id = id;
        templateDTO.title = title;
        templateDTO.fields = fields;
        templateDTO.activities = activities;
        templateDTO.allowedUserGroups = allowedUserGroups;
        templateDTO.verificationSteps = verificationSteps;
        templateDTO.templateVerificationSteps = templateVerificationSteps;
        return templateDTO;
    }
}
