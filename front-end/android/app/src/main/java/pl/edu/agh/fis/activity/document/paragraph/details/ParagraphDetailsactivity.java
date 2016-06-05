package pl.edu.agh.fis.activity.document.paragraph.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.adapter.list.paragraph.ParagraphDetailsListAdapter;
import pl.edu.agh.fis.dto.document.ParagraphDTO;

/**
 * Created by wemstar on 2016-05-29.
 */
@EActivity(R.layout.activity_floating_button_list)
@OptionsMenu(R.menu.save_only_menu)
public class ParagraphDetailsActivity extends AppCompatActivity {
    public static final String PARAGRAPH_INTENT = "PARAGRAPH_INTENT";

    private ParagraphDTO paragraph;
    private int currentParagraphPosition;

    @ViewById(R.id.listView)
    ListView listView;

    @Bean
    ParagraphDetailsListAdapter adapter;

    @AfterViews
    void initalize() {
        paragraph = (ParagraphDTO) getIntent().getSerializableExtra(PARAGRAPH_INTENT);
        if(paragraph.content == null)
            paragraph.content = new ArrayList<>();
        adapter.setParagraph(paragraph);
        listView.setAdapter(adapter);
    }

    @Click(R.id.floatingButton)
    void updateBookmarksClicked() {
        paragraph.content.add("");
        adapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_save)
    void saveDocument() {
        Intent intent = new Intent();
        intent.putExtra(PARAGRAPH_INTENT, paragraph);
        setResult(RESULT_OK, intent);
        finish();
    }

    @ItemLongClick(R.id.listView)
    void deleteParagraph(int position) {
        paragraph.content.remove(position);
        adapter.notifyDataSetChanged();
    }
}
