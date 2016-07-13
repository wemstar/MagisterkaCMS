package pl.edu.agh.fis.builder.dto.document;

import java.util.Date;
import java.util.List;

import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;

/**
 * Created by wemstar on 2016-05-14.
 */
public class DocumentDTOBuilder {
    private String id;
    private String title;
    private Date date;
    private Long author;
    private List<ChapterDTO> chapters;
    private List<ActivityDTO> activities;
    private List<VerificationStepDTO> verificationSteps;
    private List<Long> allowedUserGroups;

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

    public DocumentDTOBuilder activities(List<ActivityDTO> activities) {
        this.activities = activities;
        return this;
    }

    public DocumentDTOBuilder verificationSteps(List<VerificationStepDTO> verificationSteps) {
        this.verificationSteps = verificationSteps;
        return this;
    }

    public DocumentDTOBuilder allowedUserGroups(List<Long> allowedUserGroups) {
        this.allowedUserGroups = allowedUserGroups;
        return this;
    }


    public DocumentDTO build() {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.id = id;
        documentDTO.title = title;
        documentDTO.date = date;
        documentDTO.author = author;
        documentDTO.chapters = chapters;
        documentDTO.activities = activities;
        documentDTO.verificationSteps = verificationSteps;
        documentDTO.allowedUserGroups = allowedUserGroups;
        return documentDTO;
    }
}
