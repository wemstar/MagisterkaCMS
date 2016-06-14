package pl.edu.agh.fis.view.application.item;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.application.FieldDTO;

/**
 * Created by wemstar on 2016-06-14.
 */
@EViewGroup(R.layout.application_field_layout)
public class ApplicationFieldItem extends LinearLayout {

    private FieldDTO fieldDTO;

    public ApplicationFieldItem(Context context) {
        super(context);
    }

    @ViewById
    TextView fieldType;

    @ViewById
    EditText fieldValue;

    @ViewById
    TextInputLayout fieldNameInputLayout;

    public void bindField(FieldDTO fieldDTO) {
        this.fieldDTO = fieldDTO;
        fieldType.setText(fieldDTO.type.getValue());
        fieldValue.setText(fieldDTO.value);
        fieldNameInputLayout.setHint(fieldDTO.filedName);
    }
}
