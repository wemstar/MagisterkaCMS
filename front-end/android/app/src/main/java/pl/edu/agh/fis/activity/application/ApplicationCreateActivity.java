package pl.edu.agh.fis.activity.application;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.adapter.list.application.ApplicationDetailsAdapter;
import pl.edu.agh.fis.builder.dto.application.ApplicationDTOBuilder;
import pl.edu.agh.fis.builder.dto.application.FieldDTOBuilder;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.FieldDTO;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;
import pl.edu.agh.fis.rest.application.ApplicationClient;

/**
 * Created by wemstar on 2016-06-14.
 */
@EActivity(R.layout.activity_create_application)
@OptionsMenu(R.menu.save_only_menu)
public class ApplicationCreateActivity extends AppCompatActivity {

    public static final String APPLICATION_TEMPLATE = "APPLICATION_TEMPLATE";

    @ViewById
    ListView listView;

    @ViewById
    TextView applicationName;

    @Bean
    ApplicationDetailsAdapter adapter;

    @RestService
    ApplicationClient applicationClient;

    private ApplicationDTO applicationDTO;

    private ApplicationTemplateDTO applicationTemplateDTO;

    @AfterViews
    void initView() {
        applicationTemplateDTO = (ApplicationTemplateDTO) getIntent().getSerializableExtra(APPLICATION_TEMPLATE);

        generateApplicationFromTemplate();
        adapter.setApplication(applicationDTO);
        listView.setAdapter(adapter);

        applicationName.setText(applicationDTO.title);
    }

    @OptionsItem(R.id.action_save)
    void actionSaveClicked() {
        saveApplication();
        finish();
    }

    @Background
    void saveApplication() {
        applicationClient.createApplication(applicationDTO);
    }

    private void generateApplicationFromTemplate() {
        List<FieldDTO> fields = new ArrayList<>();
        for (TemplateFieldsDTO field : applicationTemplateDTO.fields) {
            fields.add(FieldDTOBuilder.aFieldDTO()
                    .fieldType(field.type)
                    .title(field.filedName)
                    .fieldValue(field.defaultValue)
                    .build());
        }

        applicationDTO = ApplicationDTOBuilder.anApplicationTemplateDTO()
                .fields(fields)
                .title(applicationTemplateDTO.title)
                .templateId(applicationTemplateDTO.id)
                .build();

    }
}
