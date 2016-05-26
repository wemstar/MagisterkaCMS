package pl.edu.agh.fis.dto.document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wemstar on 2016-04-13.
 */
public class ChapterDTO implements Serializable{
    public String number;
    public String name;
    public List<ParagraphDTO> paragraphs;
}
