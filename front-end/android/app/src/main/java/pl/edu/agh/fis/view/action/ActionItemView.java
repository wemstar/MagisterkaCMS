package pl.edu.agh.fis.view.action;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.activity.ActivityDTO;

/**
 * Created by wemstar on 2016-06-22.
 */
@EViewGroup(R.layout.static_text_layout)
public class ActionItemView extends LinearLayout {

    private ActivityDTO activityDTO;

    public ActionItemView(Context context) {
        super(context);
    }

    @ViewById(R.id.chapter_title)
    TextView activityComment;

    public void bindField(ActivityDTO activityDTO) {
        this.activityDTO = activityDTO;
        activityComment.setText(activityDTO.comment.content);
    }
}
