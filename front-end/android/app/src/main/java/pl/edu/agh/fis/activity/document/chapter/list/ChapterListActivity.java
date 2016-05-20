package pl.edu.agh.fis.activity.document.chapter.list;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.adapter.list.chapter.ChapterListAdapter;
import pl.edu.agh.fis.builder.dto.document.DocumentDTOBuilder;
import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;

/**
 * Created by wemstar on 2016-05-18.
 */
@EActivity(R.layout.activity_chapter_list)
@OptionsMenu(R.menu.chapter_list_menu)
public class ChapterListActivity extends AppCompatActivity {

    DocumentDTO document;

    @Bean
    ChapterListAdapter adapter;

    @ViewById
    ListView chapterList;

    @AfterViews
    void initTable() {
        document = DocumentDTOBuilder.aDocumentDTO().chapters(new ArrayList<ChapterDTO>()).build();
        adapter.setDocument(document);
        chapterList.setAdapter(adapter);
    }
}
