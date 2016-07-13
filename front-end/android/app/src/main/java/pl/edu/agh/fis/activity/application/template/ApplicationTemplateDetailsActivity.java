package pl.edu.agh.fis.activity.application.template;

import android.content.Intent;
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
import java.util.List;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.action.CreateActionActivity;
import pl.edu.agh.fis.activity.action.CreateActionActivity_;
import pl.edu.agh.fis.activity.verification.VerificationActivity;
import pl.edu.agh.fis.activity.verification.VerificationActivity_;
import pl.edu.agh.fis.adapter.list.action.ActionsAdapter;
import pl.edu.agh.fis.adapter.list.application.template.ApplicationTemplateDetailsAdapter;
import pl.edu.agh.fis.builder.dto.application.template.ApplicationTemplateDTOBuilder;
import pl.edu.agh.fis.builder.dto.application.template.TemplateFieldsDTOBuilder;
import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.application.template.FieldTypeDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;
import pl.edu.agh.fis.rest.application.tempalte.ApplicationTemplateClient;

/**
 * Created by wemstar on 2016-06-07.
 */
@EActivity(R.layout.activity_create_template)
@OptionsMenu(R.menu.create_template_menu)
public class ApplicationTemplateDetailsActivity extends AppCompatActivity {

    public static final String TEMPLATE_INTENT = "DOCUMENT_INTENT";

    ApplicationTemplateDTO template;

    @RestService
    ApplicationTemplateClient templateClient;

    @Bean
    ApplicationTemplateDetailsAdapter adapter;

    @Bean
    ActionsAdapter actionsAdapter;

    @ViewById
    EditText templateName;

    @ViewById(R.id.listView)
    ListView listView;

    @ViewById
    ListView commentList;

    @AfterViews
    void initTable() {
        ApplicationTemplateDTO template = (ApplicationTemplateDTO) getIntent().getSerializableExtra(TEMPLATE_INTENT);
        if (template == null) {
            template = ApplicationTemplateDTOBuilder.anApplicationTemplateDTO()
                    .fields(new ArrayList<TemplateFieldsDTO>())
                    .activities(new ArrayList<ActivityDTO>())
                    .verificationSteps(new ArrayList<VerificationStepDTO>())
                    .templateVerificationSteps(new ArrayList<VerificationStepDTO>())
                    .allowedUserGroups(new ArrayList<Long>())
                    .build();
        }
        actionsAdapter.setActions(template.activities);
        commentList.setAdapter(actionsAdapter);
        this.template = template;
        adapter.setTemplate(template);
        listView.setAdapter(adapter);
    }

    @Click(R.id.floatingButton)
    void updateBookmarksClicked() {
        Intent intent = new Intent(this, CreateActionActivity_.class);
        startActivityForResult(intent, 1);
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

    @OptionsItem(R.id.action_add_item)
    void actionAddItem() {
        template.fields.add(TemplateFieldsDTOBuilder.aTemplateFieldsDTO().type(FieldTypeDTO.STRING_TYPE).build());
        adapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_template_verification_steps)
    void templateVerificationSteps() {
        Intent intent = new Intent(this, VerificationActivity_.class);
        intent.putExtra(VerificationActivity.VERIFICATION_STEPS_INTENT, new ArrayList<>(template.templateVerificationSteps));
        intent.putExtra(VerificationActivity.VERIFICATION_ELEMENT_DI, template.id);
        startActivityForResult(intent, 2);
    }

    @OptionsItem(R.id.action_verification_steps)
    void verificationSteps() {
        Intent intent = new Intent(this, VerificationActivity_.class);
        intent.putExtra(VerificationActivity.VERIFICATION_STEPS_INTENT, new ArrayList<>(template.verificationSteps));
        intent.putExtra(VerificationActivity.VERIFICATION_ELEMENT_DI, template.id);
        startActivityForResult(intent, 3);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                template.activities.add((ActivityDTO) data.getSerializableExtra(CreateActionActivity.ACTION_INTENT));
                actionsAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                template.templateVerificationSteps = (List<VerificationStepDTO>) data.getSerializableExtra(VerificationActivity.VERIFICATION_STEPS_INTENT);
            }
        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                template.verificationSteps = (List<VerificationStepDTO>) data.getSerializableExtra(VerificationActivity.VERIFICATION_STEPS_INTENT);
            }
        }
    }
}
