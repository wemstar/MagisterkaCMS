package pl.edu.agh.fis.pdf.helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPTable;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.FieldDTO;

import java.util.Date;

/**
 * Created by wemstar on 2016-09-05.
 */
public class ApplicationHelper {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    public static void addMetaData(Document document, ApplicationDTO documentSource) {
        document.addTitle(documentSource.title);
        document.addKeywords("Java, PDF, iText");
        document.addAuthor(documentSource.author.toString());
        document.addCreator(documentSource.author.toString());
    }

    public static void addTitlePage(Document document, ApplicationDTO documentSource) throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph(documentSource.title, catFont));

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph("Report generated by: " + documentSource.author + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                smallBold));
        addEmptyLine(preface, 3);
        document.add(preface);
    }

    public static void addContent(Document document, ApplicationDTO documentSource) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        // header row:
        table.addCell("Nazwa Pola");
        table.addCell("Wartość");
        table.setHeaderRows(1);
        table.setSkipFirstHeader(true);
        // many data rows:
        for (FieldDTO field: documentSource.fields) {
            table.addCell(field.title);
            table.addCell(field.fieldValue);
        }
        document.add(table);
    }

    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}