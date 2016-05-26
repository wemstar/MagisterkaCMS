package pl.edu.agh.fis.builder.dto.document;

import java.util.List;

import pl.edu.agh.fis.dto.document.ParagraphDTO;

/**
 * Created by wemstar on 2016-05-26.
 */
public class ParagraphDTOBuilder {
    List<String> content;

    private ParagraphDTOBuilder() {
    }

    public static ParagraphDTOBuilder aParagraphDTO() {
        return new ParagraphDTOBuilder();
    }

    public ParagraphDTOBuilder content(List<String> content) {
        this.content = content;
        return this;
    }

    public ParagraphDTO build() {
        ParagraphDTO paragraphDTO = new ParagraphDTO();
        paragraphDTO.content = content;
        return paragraphDTO;
    }
}
