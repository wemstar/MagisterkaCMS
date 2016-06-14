package pl.edu.agh.fis.dto.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by wemstar on 2016-04-13.
 */
@JsonIgnoreProperties({"_links"})
public class ApplicationDTO {
    public String id;
    public String title;
    public List<FieldDTO> fields;
}
