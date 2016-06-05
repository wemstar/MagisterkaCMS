package pl.edu.agh.fis.view.document.chapter;

import android.content.Context;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.document.ChapterDTO;

/**
 * Created by wemstar on 2016-05-26.
 */
@EViewGroup(R.layout.static_text_layout)
public class ChapterItemView extends RelativeLayout {

    private ChapterDTO chapterDTO;

    public ChapterItemView(Context context) {
        super(context);
    }

    @ViewById(R.id.chapter_title)
    TextView chapterTitile;

    public void bindChapter(ChapterDTO chapterDTO) {
        this.chapterDTO = chapterDTO;
        chapterTitile.setText(chapterDTO.name);
    }
}
