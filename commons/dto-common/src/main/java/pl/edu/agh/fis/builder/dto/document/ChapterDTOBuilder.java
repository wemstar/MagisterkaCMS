package pl.edu.agh.fis.builder.dto.document;

import java.util.List;

import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.ParagraphDTO;

/**
 * Created by wemstar on 2016-05-14.
 */
public class ChapterDTOBuilder {
    private String number;
    private String name;
    private List<ParagraphDTO> paragraphs;

    private ChapterDTOBuilder() {
    }

    public static ChapterDTOBuilder aChapterDTO() {
        return new ChapterDTOBuilder();
    }

    public ChapterDTOBuilder number(String number) {
        this.number = number;
        return this;
    }

    public ChapterDTOBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ChapterDTOBuilder paragraphs(List<ParagraphDTO> paragraphs) {
        this.paragraphs = paragraphs;
        return this;
    }

    public ChapterDTO build() {
        ChapterDTO chapterDTO = new ChapterDTO();
        chapterDTO.number = number;
        chapterDTO.name = name;
        chapterDTO.paragraphs = paragraphs;
        return chapterDTO;
    }
}
