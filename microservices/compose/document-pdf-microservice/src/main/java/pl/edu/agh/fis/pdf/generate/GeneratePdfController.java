package pl.edu.agh.fis.pdf.generate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.fis.pdf.service.GeneratePdfService;

import java.io.ByteArrayOutputStream;

/**
 * Created by wemstar on 2016-08-14.
 */
@RestController
@RequestMapping("/pdf/generation")
public class GeneratePdfController {

    @Autowired
    GeneratePdfService service;

    @RequestMapping(method = RequestMethod.GET, value = "/application/{documentId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable String documentId) throws DocumentException {
        byte[] byteArray = service.generatePdf(documentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
        return response;
    }
}
