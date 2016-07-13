package pl.edu.agh.fis.utils;

import java.beans.PropertyEditorSupport;

/**
 * Created by wemstar on 2016-07-03.
 */
public class ElementTypeConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        String capitalized = text.toUpperCase();
        ElementType currency = ElementType.valueOf(capitalized);
        setValue(currency);
    }
}
