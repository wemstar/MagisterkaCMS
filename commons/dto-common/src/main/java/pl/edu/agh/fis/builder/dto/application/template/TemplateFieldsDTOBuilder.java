package pl.edu.agh.fis.builder.dto.application.template;

import pl.edu.agh.fis.dto.application.template.FieldTypeDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;

/**
 * Created by wemstar on 2016-06-07.
 */
public class TemplateFieldsDTOBuilder {

    private FieldTypeDTO type;
    private String filedName;
    private String defaultValue;

    private TemplateFieldsDTOBuilder() {
    }

    public static TemplateFieldsDTOBuilder aTemplateFieldsDTO() {
        return new TemplateFieldsDTOBuilder();
    }

    public TemplateFieldsDTOBuilder type(FieldTypeDTO type) {
        this.type = type;
        return this;
    }

    public TemplateFieldsDTOBuilder filedName(String filedName) {
        this.filedName = filedName;
        return this;
    }

    public TemplateFieldsDTOBuilder defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }



    public TemplateFieldsDTO build() {
        TemplateFieldsDTO templateDTO = new TemplateFieldsDTO();
        templateDTO.type = type;
        templateDTO.filedName = filedName;
        templateDTO.defaultValue = defaultValue;
        return templateDTO;
    }
}
