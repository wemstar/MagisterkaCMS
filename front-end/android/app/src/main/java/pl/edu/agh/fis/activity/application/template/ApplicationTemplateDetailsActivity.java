package pl.edu.agh.fis.activity.application.template;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FocusChange;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.adapter.list.application.template.ApplicationTemplateDetailsAdapter;
import pl.edu.agh.fis.builder.dto.application.template.ApplicationTemplateDTOBuilder;
import pl.edu.agh.fis.builder.dto.application.template.TemplateFieldsDTOBuilder;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.application.template.FieldTypeDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;
import pl.edu.agh.fis.rest.application.tempalte.ApplicationTemplateClient;

/**
 * Created by wemstar on 2016-06-07.
 */
@EActivity(R.layout.activity_create_template)
@OptionsMenu(R.menu.save_only_menu)
public class ApplicationTemplateDetailsActivity extends AppCompatActivity {

    public static final String TEMPLATE_INTENT = "DOCUMENT_INTENT";

    ApplicationTemplateDTO template;

    @RestService
    ApplicationTemplateClient templateClient;

    @Bean
    ApplicationTemplateDetailsAdapter adapter;

    @ViewById
    EditText templateName;

    @ViewById(R.id.listView)
    ListView listView;

    @AfterViews
    void initTable() {
        ApplicationTemplateDTO template = (ApplicationTemplateDTO) getIntent().getSerializableExtra(TEMPLATE_INTENT);
        if (template == null) {
            template = ApplicationTemplateDTOBuilder.anApplicationTemplateDTO().fields(new ArrayList<TemplateFieldsDTO>()).build();
        }
        this.template = template;
        adapter.setTemplate(template);
        listView.setAdapter(adapter);
    }

    @Click(R.id.floatingButton)
    void updateBookmarksClicked() {
        template.fields.add(TemplateFieldsDTOBuilder.aTemplateFieldsDTO().type(FieldTypeDTO.STRING_TYPE).build());
        adapter.notifyDataSetChanged();
    }

    @ItemLongClick(R.id.listView)
    public void itemCLicked(int position) {
        template.fields.remove(position);
        adapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_save)
    void actionSaveClicked() {
        saveDocument();
        finish();
    }

    @FocusChange(R.id.templateName)
    void focusChangedOnTemplateName(EditText editText) {
        template.title = editText.getText().toString();
    }

    @Background
    void saveDocument() {
        if (template.id != null) {
            templateClient.updateTemplate(template.id, template);
        } else {
            templateClient.createTemplate(template);
        }
    }
}
