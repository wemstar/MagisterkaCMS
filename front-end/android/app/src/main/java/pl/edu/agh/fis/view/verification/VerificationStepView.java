package pl.edu.agh.fis.view.verification;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;

import java.util.List;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.verification.VerificationActivity;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;
import pl.edu.agh.fis.utils.UserContext;

/**
 * Created by wemstar on 2016-07-03.
 */
@EViewGroup(R.layout.static_text_button_layout)
public class VerificationStepView extends RelativeLayout {

    private VerificationStepDTO verificationStep;

    public VerificationStepView(Context context) {
        super(context);
    }

    private int position;
    private VerificationActivity verificationActivity;

    @Bean
    UserContext userContext;

    @ViewById(R.id.chapter_title)
    TextView textView;

    @ViewById(R.id.confirm_button)
    Button button;

    @ColorRes(R.color.colorPrimaryText)
    int normalTextColor;

    @ColorRes(R.color.colorIcons)
    int selectedTextColor;

    @ColorRes(R.color.transparent)
    int normalBackgroundColor;

    @ColorRes(R.color.colorAccent)
    int selectedBackgroundColor;

    @Click(R.id.confirm_button)
    void confirmClicked() {
        verificationStep.filled = true;
    }

    @Click(R.id.edit_button)
    void editButtonCLicked() {
        verificationActivity.listItemClicked(position);
    }

    @Click(R.id.delete_button)
    void deleteClicked() {
        verificationActivity.listItemLongClicked(position);
    }


    public void setVerificationStep(VerificationStepDTO verificationStep, int position, VerificationActivity activity) {
        this.verificationStep = verificationStep;
        this.position = position;
        this.verificationActivity = activity;
        textView.setText(verificationStep.id);
        textView.setTextColor(verificationStep.filled ? selectedTextColor : normalTextColor);
        textView.setBackgroundColor(verificationStep.filled ? selectedBackgroundColor : normalBackgroundColor);
        button.setEnabled(isConfirmPossible());
    }

    private boolean isConfirmPossible() {
        List<Long> userGroupIds = userContext.getUserGroupsIds();
        userGroupIds.retainAll(verificationStep.allowedUserGroup);
        return userGroupIds.size() != 0 && !verificationStep.filled;
    }
}
