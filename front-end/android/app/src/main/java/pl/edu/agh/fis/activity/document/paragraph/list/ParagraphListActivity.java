package pl.edu.agh.fis.activity.document.paragraph.list;

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
import pl.edu.agh.fis.activity.document.paragraph.details.ParagraphDetailsActivity;
import pl.edu.agh.fis.activity.document.paragraph.details.ParagraphDetailsActivity_;
import pl.edu.agh.fis.adapter.list.paragraph.ParagraphListAdapter;
import pl.edu.agh.fis.builder.dto.document.ParagraphDTOBuilder;
import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.ParagraphDTO;


/**
 * Created by wemstar on 2016-05-24.
 */
@EActivity(R.layout.activity_floating_button_list)
@OptionsMenu(R.menu.save_only_menu)
public class ParagraphListActivity extends AppCompatActivity {
    public static final String PARAGRAPH_INTENT = "PARAGRAPH_INTENT";

    private ChapterDTO chapter;
    private int currentParagraphPosition;

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

    @ItemLongClick(R.id.listView)
    void editParagraph(int position) {
        currentParagraphPosition = position;
        Intent intent = new Intent(this,ParagraphDetailsActivity_.class);
        intent.putExtra(ParagraphDetailsActivity.PARAGRAPH_INTENT,chapter.paragraphs.get(position));
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                ParagraphDTO paragraph = (ParagraphDTO) data.getSerializableExtra(ParagraphDetailsActivity.PARAGRAPH_INTENT);
                chapter.paragraphs.set(currentParagraphPosition,paragraph);
                adapter.notifyDataSetChanged();
            }
        }
    }

    /*@ItemClick(R.id.listView)
    void deleteParagraph(int position) {
        chapter.paragraphs.remove(position);
        adapter.notifyDataSetChanged();
    }*/
}
