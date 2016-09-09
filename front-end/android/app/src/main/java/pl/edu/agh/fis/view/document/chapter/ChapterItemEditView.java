package pl.edu.agh.fis.view.document.chapter;

import android.content.Context;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.document.ChapterDTO;

/**
 * Created by wemstar on 2016-05-14.
 */
@EViewGroup(R.layout.edit_text_layout)
public class ChapterItemEditView extends RelativeLayout {

    private ChapterDTO chapterDTO;

    public ChapterItemEditView(Context context) {
        super(context);
    }

    @ViewById(R.id.chapter_title)
    EditText chapterTitile;

    public void bindChapter(ChapterDTO chapterDTO) {
        this.chapterDTO = chapterDTO;
        chapterTitile.setText(chapterDTO.name);
    }

    @TextChange(R.id.chapter_title)
    void focusChangedOnHelloTextView(TextView editText) {
        chapterDTO.name = editText.getText().toString();
    }
}
