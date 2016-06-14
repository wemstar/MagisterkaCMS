package pl.edu.agh.fis.dto.application.template;

import java.io.Serializable;

/**
 * Created by wemstar on 2016-04-13.
 */
public enum FieldTypeDTO implements Serializable {
    STRING_TYPE("String"),
    INTEGER_TYPE("Integer"),
    DATE_TYPE("Date"),
    DECIMAL_TYPE("Decimal"),
    BOOL_TYPE("Bool");

    private final String value;

    FieldTypeDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FieldTypeDTO getFromString(String stringValue) {
        switch (stringValue) {
            case "String":
                return STRING_TYPE;
            case "Decimal":
                return DECIMAL_TYPE;
            case "Date":
                return DATE_TYPE;
            case "Integer":
                return INTEGER_TYPE;
            case "Bool":
                return BOOL_TYPE;
            default:
                return STRING_TYPE;
        }
    }
}
