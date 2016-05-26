package pl.edu.agh.fis.activity.document.paragraph.list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.adapter.list.paragraph.ParagraphListAdapter;
import pl.edu.agh.fis.builder.dto.document.ParagraphDTOBuilder;
import pl.edu.agh.fis.dto.document.ChapterDTO;

/**
 * Created by wemstar on 2016-05-24.
 */
@EActivity(R.layout.activity_floating_button_list)
@OptionsMenu(R.menu.save_only_menu)
public class ParagraphListActivity extends AppCompatActivity {
    public static final String PARAGRAPH_INTENT = "PARAGRAPH_INTENT";

    private ChapterDTO chapter;

    @ViewById(R.id.listView)
    ListView listView;

    @Bean
    ParagraphListAdapter adapter;

    @AfterViews
    void initalize() {
        chapter = (ChapterDTO) getIntent().getSerializableExtra(PARAGRAPH_INTENT);
        if(chapter.paragraphs == null)
            chapter.paragraphs = new ArrayList<>();
        adapter.setChapter(chapter);
        listView.setAdapter(adapter);
    }

    @Click(R.id.floatingButton)
    void updateBookmarksClicked() {
        chapter.paragraphs.add(ParagraphDTOBuilder.aParagraphDTO().content(new ArrayList<String>()).build());
        adapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_save)
    void saveDocument() {
        Intent intent = new Intent();
        intent.putExtra(PARAGRAPH_INTENT,chapter);
        setResult(RESULT_OK, intent);
        finish();
    }
}
