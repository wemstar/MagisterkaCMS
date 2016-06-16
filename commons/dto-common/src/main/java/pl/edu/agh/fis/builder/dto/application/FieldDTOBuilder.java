package pl.edu.agh.fis.builder.dto.application;

import pl.edu.agh.fis.dto.application.FieldDTO;
import pl.edu.agh.fis.dto.application.template.FieldTypeDTO;

/**
 * Created by wemstar on 2016-06-14.
 */
public class FieldDTOBuilder {
    private FieldTypeDTO fieldType;
    private String title;
    private String fieldValue;

    private FieldDTOBuilder() {
    }

    public static FieldDTOBuilder aFieldDTO() {
        return new FieldDTOBuilder();
    }

    public FieldDTOBuilder fieldType(FieldTypeDTO fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public FieldDTOBuilder title(String title) {
        this.title = title;
        return this;
    }

    public FieldDTOBuilder fieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
        return this;
    }


    public FieldDTO build() {
        FieldDTO templateDTO = new FieldDTO();
        templateDTO.fieldType = fieldType;
        templateDTO.title = title;
        templateDTO.fieldValue = fieldValue;
        return templateDTO;
    }
}
