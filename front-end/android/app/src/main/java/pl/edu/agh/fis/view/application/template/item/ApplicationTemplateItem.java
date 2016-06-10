package pl.edu.agh.fis.view.application.template.item;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;

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

    @AfterViews
    void initViews() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.application_template_filed_type, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fieldTypeSpinner.setAdapter(adapter);
    }

}
