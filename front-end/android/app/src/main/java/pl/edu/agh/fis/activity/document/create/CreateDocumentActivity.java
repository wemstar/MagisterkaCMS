package pl.edu.agh.fis.activity.document.create;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ExpandableListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.adapter.list.document.element.DocumentElementAdapter;
import pl.edu.agh.fis.builder.dto.document.ChapterDTOBuilder;
import pl.edu.agh.fis.builder.dto.document.DocumentDTOBuilder;
import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.dto.document.ParagraphDTO;
import pl.edu.agh.fis.rest.document.DocumentClient;

@EActivity(R.layout.activity_create_document)
@OptionsMenu(R.menu.create_document_menu)
public class CreateDocumentActivity extends AppCompatActivity {

    DocumentDTO document;

    @ViewById
    ExpandableListView documentContent;

    @Bean
    DocumentElementAdapter adapter;

    @RestService
    DocumentClient documentClient;

    @AfterViews
    void initTable() {
        document = DocumentDTOBuilder.aDocumentDTO().chapters(new ArrayList<ChapterDTO>()).build();
        adapter.setDocument(document);
        documentContent.setAdapter(adapter);
    }

    @FocusChange(R.id.documentTitle)
    void focusChangedOnHelloTextView(EditText editText) {
        document.title = editText.getText().toString();
    }

    @OptionsItem(R.id.action_add_chapter)
    void addChapter() {
        document.chapters.add(ChapterDTOBuilder.aChapterDTO().paragraphs(new ArrayList<ParagraphDTO>()).build());
        adapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_save)
    void saveDocument() {
        sendDocument();
        finish();
    }

    @Background
    void sendDocument() {
        documentClient.createDocument(document);
    }

}
