package pl.edu.agh.fis.activity.verification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.user.group.UserGroupListActivity;
import pl.edu.agh.fis.activity.user.group.UserGroupListActivity_;
import pl.edu.agh.fis.adapter.list.verification.VerificationAdapter;
import pl.edu.agh.fis.builder.dto.verification.VerificationStepDTOBuilder;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;

/**
 * Created by wemstar on 2016-07-03.
 */
@EActivity(R.layout.activity_floating_button_list)
@OptionsMenu(R.menu.save_only_menu)
public class VerificationActivity extends AppCompatActivity {

    public final static String VERIFICATION_STEPS_INTENT = "VERIFICATION_STEPS_INTENT";
    public final static String VERIFICATION_ELEMENT_DI = "VERIFICATION_ELEMENT_DI";

    private String id;
    private List<VerificationStepDTO> verificationSteps;
    private int selectedStep;

    @Bean
    VerificationAdapter adapter;
    @ViewById
    ListView listView;

    @AfterViews
    void initActivity() {
        verificationSteps = (List<VerificationStepDTO>) getIntent().getSerializableExtra(VERIFICATION_STEPS_INTENT);
        id = getIntent().getStringExtra(VERIFICATION_ELEMENT_DI);
        adapter.setVerificationSteps(verificationSteps);
        adapter.setActivity(this);
        listView.setAdapter(adapter);

    }

    public void listItemClicked(int position) {
        this.selectedStep = position;
        Intent intent = new Intent(this, UserGroupListActivity_.class);
        intent.putExtra(UserGroupListActivity.USER_GROUP_INTENT, new ArrayList<>(verificationSteps.get(position).allowedUserGroup));
        startActivityForResult(intent, 1);
    }


    public void listItemLongClicked(int position) {
        verificationSteps.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Click(R.id.floatingButton)
    void newVerificationStep() {
        verificationSteps.add(VerificationStepDTOBuilder.aVerificationStepDTOBuilder().filled(false).allowedUserGroup(new ArrayList<Long>()).build());
        adapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_save)
    void actionSave() {
        Intent intent = new Intent();
        intent.putExtra(VERIFICATION_STEPS_INTENT, new ArrayList<>(verificationSteps));
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                this.verificationSteps.get(selectedStep).allowedUserGroup = (List<Long>) data.getSerializableExtra(UserGroupListActivity.USER_GROUP_INTENT);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
