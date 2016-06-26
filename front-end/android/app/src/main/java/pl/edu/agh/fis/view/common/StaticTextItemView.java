package pl.edu.agh.fis.view.common;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;

/**
 * Created by wemstar on 2016-06-20.
 */
@EViewGroup(R.layout.static_text_layout)
public class StaticTextItemView extends RelativeLayout {

    public StaticTextItemView(Context context) {
        super(context);
    }

    @ViewById(R.id.chapter_title)
    TextView chapterTitile;

    public void bindLabel(String labelText) {
        chapterTitile.setText(labelText);
    }
}
