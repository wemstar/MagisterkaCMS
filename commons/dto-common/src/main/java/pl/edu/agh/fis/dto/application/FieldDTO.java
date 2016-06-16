package pl.edu.agh.fis.dto.application;

import java.io.Serializable;

import pl.edu.agh.fis.dto.application.template.FieldTypeDTO;

/**
 * Created by wemstar on 2016-04-13.
 */
public class FieldDTO implements Serializable {
    public String title;
    public String fieldValue;
    public FieldTypeDTO fieldType;
}
