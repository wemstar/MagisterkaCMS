package pl.edu.agh.fis.activity.document.chapter.list;

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

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.document.paragraph.list.ParagraphListActivity;
import pl.edu.agh.fis.activity.document.paragraph.list.ParagraphListActivity_;
import pl.edu.agh.fis.adapter.list.chapter.ChapterListAdapter;
import pl.edu.agh.fis.builder.dto.document.ChapterDTOBuilder;
import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;

/**
 * Created by wemstar on 2016-05-18.
 */
@EActivity(R.layout.activity_floating_button_list)
@OptionsMenu(R.menu.save_only_menu)
public class ChapterListActivity extends AppCompatActivity {

    public static final String DOCUMENT_INTENT = "DOCUMENT_INTENT";

    DocumentDTO document;
    private Integer currentChapterPosition;

    @Bean
    ChapterListAdapter adapter;

    @ViewById(R.id.listView)
    ListView listView;

    @AfterViews
    void initTable() {
        document = (DocumentDTO) getIntent().getSerializableExtra(DOCUMENT_INTENT);
        adapter.setDocument(document);
        listView.setAdapter(adapter);
    }

    @Click(R.id.floatingButton)
    void updateBookmarksClicked() {
        document.chapters.add(ChapterDTOBuilder.aChapterDTO().build());
        adapter.notifyDataSetChanged();
    }

    @ItemLongClick(R.id.listView)
    public void itemLongCLicked(int position) {
        currentChapterPosition = position;
        Intent intent = new Intent(this,ParagraphListActivity_.class);
        intent.putExtra(ParagraphListActivity.PARAGRAPH_INTENT,document.chapters.get(position));
        startActivityForResult(intent,1);
    }

    @OptionsItem(R.id.action_save)
    void saveDocument() {
        Intent intent = new Intent();
        intent.putExtra(DOCUMENT_INTENT,document);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                ChapterDTO chapter = (ChapterDTO) data.getSerializableExtra(ParagraphListActivity.PARAGRAPH_INTENT);
                document.chapters.set(currentChapterPosition,chapter);
                adapter.notifyDataSetChanged();
            }
        }
    }

}
