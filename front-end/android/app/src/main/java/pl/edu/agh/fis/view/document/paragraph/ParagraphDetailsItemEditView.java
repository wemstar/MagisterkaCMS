package pl.edu.agh.fis.view.document.paragraph;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.document.ParagraphDTO;

/**
 * Created by wemstar on 2016-05-29.
 */
@EViewGroup(R.layout.edit_text_layout)
public class ParagraphDetailsItemEditView extends LinearLayout {

    private ParagraphDTO paragraph;
    private int position;

    @ViewById(R.id.chapter_title)
    EditText editText;

    public ParagraphDetailsItemEditView(Context context) {
        super(context);
    }

    public void bindParagraph(ParagraphDTO paragraph, int position) {
        this.paragraph = paragraph;
        this.position = position;
        editText.setText(paragraph.content.get(position));
    }

    @ViewById(R.id.chapter_title)
    EditText chapterTitile;

    @TextChange(R.id.chapter_title)
    void focusChangedOnHelloTextView(TextView editText) {
        paragraph.content.set(position, editText.getText().toString());
    }
}
