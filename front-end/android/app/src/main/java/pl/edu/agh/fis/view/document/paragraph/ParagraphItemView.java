package pl.edu.agh.fis.view.document.paragraph;

import android.content.Context;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;

import pl.edu.agh.fis.R;

/**
 * Created by wemstar on 2016-05-14.
 */
@EViewGroup(R.layout.paragraph_layout)
public class ParagraphItemView  extends LinearLayout {
    public ParagraphItemView(Context context) {
        super(context);
    }
}
