package pl.edu.agh.fis.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wemstar on 2016-04-13.
 */
@JsonIgnoreProperties({"_links"})
public class DocumentDTO implements Serializable {

    public String id;
    public String title;
    public Date date;
    public Long author;
    public List<ChapterDTO> chapters;
}
