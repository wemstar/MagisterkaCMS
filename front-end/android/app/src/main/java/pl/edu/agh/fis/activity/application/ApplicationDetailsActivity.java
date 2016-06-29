package pl.edu.agh.fis.activity.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.action.CreateActionActivity;
import pl.edu.agh.fis.activity.action.CreateActionActivity_;
import pl.edu.agh.fis.adapter.list.action.ActionsAdapter;
import pl.edu.agh.fis.adapter.list.application.ApplicationFieldsAdapter;
import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.rest.application.ApplicationClient;

/**
 * Created by wemstar on 2016-06-22.
 */
@EActivity(R.layout.activity_details_application)
@OptionsMenu(R.menu.save_only_menu)
public class ApplicationDetailsActivity extends AppCompatActivity {
    public static final String APPLICATION_DETAILS = "APPLICATION_DETAILS";

    @ViewById
    ListView applicationFiledListView;

    @ViewById
    ListView applicationActionsListView;

    @ViewById
    TextView applicationName;

    @Bean
    ApplicationFieldsAdapter adapterFields;

    @Bean
    ActionsAdapter adapterActions;

    @RestService
    ApplicationClient applicationClient;

    private ApplicationDTO applicationDTO;


    @AfterViews
    void initView() {
        applicationDTO = (ApplicationDTO) getIntent().getSerializableExtra(APPLICATION_DETAILS);
        if (applicationDTO.activities == null) {
            applicationDTO.activities = new ArrayList<>();
        }
        adapterFields.setApplication(applicationDTO);
        adapterActions.setActions(applicationDTO.activities);
        applicationFiledListView.setAdapter(adapterFields);
        applicationActionsListView.setAdapter(adapterActions);
        applicationName.setText(applicationDTO.title);
    }

    @OptionsItem(R.id.action_save)
    void actionSaveClicked() {
        applicationDTO.activities = adapterActions.getActions();
        saveApplication();
        finish();
    }

    @Click(R.id.floatingButton)
    void createAction() {
        Intent intent = new Intent(this, CreateActionActivity_.class);
        startActivityForResult(intent, 1);
    }

    @Background
    void saveApplication() {
        applicationClient.updateApplication(applicationDTO.id, applicationDTO);
    }


    private void createChapter() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                applicationDTO.activities.add((ActivityDTO) data.getSerializableExtra(CreateActionActivity.ACTION_INTENT));
                adapterActions.notifyDataSetChanged();
            }
        }
    }

}
