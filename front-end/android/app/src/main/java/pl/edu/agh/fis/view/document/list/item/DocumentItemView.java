package pl.edu.agh.fis.view.document.list.item;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.document.DocumentDTO;

/**
 * TODO: document your custom view class.
 */
@EViewGroup(R.layout.document_item_view)
public class DocumentItemView extends LinearLayout {

    @ViewById
    TextView documentTitle;

    @ViewById
    TextView documentId;

    public DocumentItemView(Context context) {
        super(context);
    }

    public void bind(DocumentDTO document) {
        documentTitle.setText(document.title);
        documentId.setText(document.id);
    }
}
