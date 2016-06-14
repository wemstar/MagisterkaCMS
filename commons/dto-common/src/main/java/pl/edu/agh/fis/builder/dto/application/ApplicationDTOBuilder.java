package pl.edu.agh.fis.builder.dto.application;

import java.util.List;

import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.FieldDTO;

/**
 * Created by wemstar on 2016-06-14.
 */
public class ApplicationDTOBuilder {

    private String id;
    private String title;
    private List<FieldDTO> fields;

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

    public ApplicationDTOBuilder fields(List<FieldDTO> fields) {
        this.fields = fields;
        return this;
    }

    public ApplicationDTO build() {
        ApplicationDTO templateDTO = new ApplicationDTO();
        templateDTO.id = id;
        templateDTO.title = title;
        templateDTO.fields = fields;
        return templateDTO;
    }
}
