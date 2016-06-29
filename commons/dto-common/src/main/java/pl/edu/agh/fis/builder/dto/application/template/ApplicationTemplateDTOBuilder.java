package pl.edu.agh.fis.builder.dto.application.template;

import java.util.List;

import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;

/**
 * Created by wemstar on 2016-06-07.
 */
public class ApplicationTemplateDTOBuilder {

    private String id;
    private String title;
    private List<TemplateFieldsDTO> fields;
    private List<ActivityDTO> activities;

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

    public ApplicationTemplateDTO build() {
        ApplicationTemplateDTO templateDTO = new ApplicationTemplateDTO();
        templateDTO.id = id;
        templateDTO.title = title;
        templateDTO.fields = fields;
        templateDTO.activities = activities;
        return templateDTO;
    }
}
