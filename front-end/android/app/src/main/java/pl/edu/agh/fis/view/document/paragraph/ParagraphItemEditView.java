package pl.edu.agh.fis.view.document.paragraph;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.document.ParagraphDTO;

/**
 * Created by wemstar on 2016-05-14.
 */
@EViewGroup(R.layout.static_text_layout)
public class ParagraphItemEditView extends LinearLayout {

    private ParagraphDTO paragraph;

    public ParagraphItemEditView(Context context) {
        super(context);
    }

    public void bindParagraph(ParagraphDTO paragraph) {
        this.paragraph = paragraph;
        if(paragraph.content != null && paragraph.content.size() > 0)
            chapterTitile.setText(paragraph.content.get(0));
    }

    @ViewById(R.id.chapter_title)
    TextView chapterTitile;


}
