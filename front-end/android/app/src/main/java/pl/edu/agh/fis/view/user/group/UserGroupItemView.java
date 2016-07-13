package pl.edu.agh.fis.view.user.group;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import pl.edu.agh.fis.R;

/**
 * Created by wemstar on 2016-07-05.
 */


@EViewGroup(R.layout.static_text_selector_layout)
public class UserGroupItemView extends LinearLayout {


    public UserGroupItemView(Context context) {
        super(context);
    }

    @ColorRes(R.color.colorPrimaryText)
    int normalTextColor;

    @ColorRes(R.color.colorIcons)
    int selectedTextColor;

    @ColorRes(R.color.transparent)
    int normalBackgroundColor;

    @ColorRes(R.color.colorAccent)
    int selectedBackgroundColor;

    @ViewById(R.id.chapter_title)
    TextView chapterTitile;


    public void bindString(String id) {
        chapterTitile.setText(id);
        chapterTitile.setTextColor(isSelected() ? selectedTextColor : normalTextColor);
        chapterTitile.setBackgroundColor(isSelected() ? selectedBackgroundColor : normalBackgroundColor);
    }

}


