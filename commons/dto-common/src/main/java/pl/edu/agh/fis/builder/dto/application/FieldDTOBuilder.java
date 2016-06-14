package pl.edu.agh.fis.builder.dto.application;

import pl.edu.agh.fis.dto.application.FieldDTO;
import pl.edu.agh.fis.dto.application.template.FieldTypeDTO;

/**
 * Created by wemstar on 2016-06-14.
 */
public class FieldDTOBuilder {
    private FieldTypeDTO type;
    private String filedName;
    private String value;

    private FieldDTOBuilder() {
    }

    public static FieldDTOBuilder aFieldDTO() {
        return new FieldDTOBuilder();
    }

    public FieldDTOBuilder type(FieldTypeDTO type) {
        this.type = type;
        return this;
    }

    public FieldDTOBuilder filedName(String filedName) {
        this.filedName = filedName;
        return this;
    }

    public FieldDTOBuilder value(String value) {
        this.value = value;
        return this;
    }


    public FieldDTO build() {
        FieldDTO templateDTO = new FieldDTO();
        templateDTO.type = type;
        templateDTO.filedName = filedName;
        templateDTO.value = value;
        return templateDTO;
    }
}
