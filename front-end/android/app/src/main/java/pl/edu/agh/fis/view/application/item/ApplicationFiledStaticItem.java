package pl.edu.agh.fis.view.application.item;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.application.FieldDTO;

/**
 * Created by wemstar on 2016-06-22.
 */
@EViewGroup(R.layout.application_static_field_layout)
public class ApplicationFiledStaticItem extends LinearLayout {

    private FieldDTO fieldDTO;

    public ApplicationFiledStaticItem(Context context) {
        super(context);
    }

    @ViewById
    TextView fieldName;

    @ViewById
    TextView fieldValue;

    public void bindField(FieldDTO fieldDTO) {
        this.fieldDTO = fieldDTO;
        fieldName.setText(fieldDTO.title);
        fieldValue.setText(fieldDTO.fieldValue);
    }
}
