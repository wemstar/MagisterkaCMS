package pl.edu.agh.fis.activity.application;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.action.CreateActionActivity;
import pl.edu.agh.fis.activity.action.CreateActionActivity_;
import pl.edu.agh.fis.activity.verification.VerificationActivity;
import pl.edu.agh.fis.activity.verification.VerificationActivity_;
import pl.edu.agh.fis.adapter.list.action.ActionsAdapter;
import pl.edu.agh.fis.adapter.list.application.ApplicationFieldsAdapter;
import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;
import pl.edu.agh.fis.rest.application.ApplicationClient;
import pl.edu.agh.fis.rest.document.DocumentPDFClient;

/**
 * Created by wemstar on 2016-06-22.
 */
@EActivity(R.layout.activity_details_application)
@OptionsMenu(R.menu.save_add_expot_menu)
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

    @RestService
    DocumentPDFClient documentPDFClient;

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

    @OptionsItem(R.id.action_verification_steps)
    void verificationSteps() {
        Intent intent = new Intent(this, VerificationActivity_.class);
        intent.putExtra(VerificationActivity.VERIFICATION_STEPS_INTENT, new ArrayList<>(applicationDTO.verificationSteps));
        intent.putExtra(VerificationActivity.VERIFICATION_ELEMENT_DI, applicationDTO.id);
        startActivityForResult(intent, 2);
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
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                applicationDTO.verificationSteps = (List<VerificationStepDTO>) data.getSerializableExtra(VerificationActivity.VERIFICATION_STEPS_INTENT);
            }
        }
    }


    @OptionsItem(R.id.action_export_pdf)
    void exportToPdf() {
        getPdfDocument(applicationDTO.id);

    }

    @Background
    void getPdfDocument(String id) {

       // String data = TextUtils.join("+", documentPDFClient.generateApplicationPDF(id));
        byte[] data = documentPDFClient.generateApplicationPDF(id).data;
        String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        File folder = new File(extStorageDirectory);
        folder.mkdir();
        File file = new File(folder, applicationDTO.title + ".pdf");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        DownloadFile(data/*Base64.decode(data, Base64.NO_PADDING)*/, file);
    }

    public static void DownloadFile(byte[] fileBytes, File directory) {
        try {

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(directory));
            bos.write(fileBytes);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
