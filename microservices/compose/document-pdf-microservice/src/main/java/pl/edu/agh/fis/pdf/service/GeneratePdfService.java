package pl.edu.agh.fis.pdf.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.fis.clients.application.ApplicationClient;
import pl.edu.agh.fis.clients.document.DocumentClient;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.pdf.helper.ApplicationHelper;
import pl.edu.agh.fis.pdf.helper.DocumentHelper;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Created by wemstar on 2016-08-14.
 */
@Service
public class GeneratePdfService {

    @Autowired
    DocumentClient documentClient;

    @Autowired
    ApplicationClient applicationClient;


    public byte[] generateDocumentPdf(String documentId) throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DocumentDTO documentSource = documentClient.getDocument(documentId).getContent();
        Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
        PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

        document.open();
        DocumentHelper.addMetaData(document, documentSource);
        DocumentHelper.addTitlePage(document, documentSource);
        DocumentHelper.addContent(document, documentSource);
        document.close();
        return byteArrayOutputStream.toByteArray();
    }


    public byte[] generateApplicationPdf(String documentId) throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ApplicationDTO documentSource = applicationClient.getApplication(documentId).getContent();
        Document document = new Document(PageSize.LETTER, 0.75F, 0.75F, 0.75F, 0.75F);
        PdfWriter.getInstance(document, byteArrayOutputStream);  // Do this BEFORE document.open()

        document.open();
        ApplicationHelper.addMetaData(document, documentSource);
        ApplicationHelper.addTitlePage(document, documentSource);
        ApplicationHelper.addContent(document, documentSource);
        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
