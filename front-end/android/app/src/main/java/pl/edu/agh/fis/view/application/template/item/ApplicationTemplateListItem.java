package pl.edu.agh.fis.view.application.template.item;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;

/**
 * Created by wemstar on 2016-06-12.
 */
@EViewGroup(R.layout.static_text_layout)
public class ApplicationTemplateListItem extends RelativeLayout {


    private ApplicationTemplateDTO templateDTO;

    public ApplicationTemplateListItem(Context context) {
        super(context);
    }

    @ViewById(R.id.chapter_title)
    TextView chapterTitile;

    public void bindTemplate(ApplicationTemplateDTO templateDTO) {
        this.templateDTO = templateDTO;
        chapterTitile.setText(templateDTO.title);
    }
}
