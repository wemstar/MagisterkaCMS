package pl.edu.agh.fis.view.application.template.item;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.application.template.FieldTypeDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;

/**
 * Created by wemstar on 2016-06-10.
 */
@EViewGroup(R.layout.application_template_item_view)
public class ApplicationTemplateItem extends LinearLayout {
    public ApplicationTemplateItem(Context context) {
        super(context);
    }

    @ViewById
    Spinner fieldTypeSpinner;

    @ViewById
    EditText fieldName;

    private ArrayAdapter<CharSequence> adapter;
    private TemplateFieldsDTO field;

    @AfterViews
    void initViews() {
        adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.application_template_filed_type, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldTypeSpinner.setAdapter(adapter);
    }

    @ItemSelect(R.id.fieldTypeSpinner)
    void itemSelected(boolean selected, int position) {
        field.type = FieldTypeDTO.getFromString(adapter.getItem(position).toString());
    }

    @FocusChange(R.id.fieldName)
    void focusChangedFieldNameEditText(EditText editText) {
        field.filedName = editText.getText().toString();
    }

    public void bindField(TemplateFieldsDTO field) {
        this.field = field;
        fieldName.setText(field.filedName);
        fieldTypeSpinner.setSelection(adapter.getPosition(field.type.getValue()));
    }

}
