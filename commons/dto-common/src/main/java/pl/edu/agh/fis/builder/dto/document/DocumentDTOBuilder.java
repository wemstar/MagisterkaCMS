package pl.edu.agh.fis.builder.dto.document;

import java.util.Date;
import java.util.List;

import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;

/**
 * Created by wemstar on 2016-05-14.
 */
public class DocumentDTOBuilder {
    private String id;
    private String title;
    private Date date;
    private Long author;
    private List<ChapterDTO> chapters;

    private DocumentDTOBuilder() {
    }

    public static DocumentDTOBuilder aDocumentDTO() {
        return new DocumentDTOBuilder();
    }

    public DocumentDTOBuilder id(String id) {
        this.id = id;
        return this;
    }

    public DocumentDTOBuilder title(String title) {
        this.title = title;
        return this;
    }

    public DocumentDTOBuilder login(Date date) {
        this.date = date;
        return this;
    }

    public DocumentDTOBuilder password(Long author) {
        this.author = author;
        return this;
    }

    public DocumentDTOBuilder chapters(List<ChapterDTO> chapters) {
        this.chapters = chapters;
        return this;
    }


    public DocumentDTO build() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.id = id;
        documentDTO.title = title;
        documentDTO.date = date;
        documentDTO.author = author;
        documentDTO.chapters = chapters;
        return documentDTO;
    }
}
