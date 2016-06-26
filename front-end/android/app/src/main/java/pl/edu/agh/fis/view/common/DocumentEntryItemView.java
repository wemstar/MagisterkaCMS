package pl.edu.agh.fis.view.common;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;

/**
 * Created by wemstar on 2016-06-20.
 */
@EViewGroup(R.layout.static_text_layout)
public class DocumentEntryItemView extends RelativeLayout {

    public DocumentEntryItemView(Context context) {
        super(context);
    }

    @ViewById(R.id.chapter_title)
    TextView chapterTitile;

    public void bindDocument(DocumentDTO document) {
        chapterTitile.setText(document.title);
    }

    public void bindApplication(ApplicationDTO application) {
        chapterTitile.setText(application.title);
    }

    public void bindTemplate(ApplicationTemplateDTO template) {
        chapterTitile.setText(template.title);
    }
}
