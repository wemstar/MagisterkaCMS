package pl.edu.agh.fis.adapter.list.verification;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import pl.edu.agh.fis.activity.verification.VerificationActivity;
import pl.edu.agh.fis.dto.verification.VerificationStepDTO;
import pl.edu.agh.fis.view.verification.VerificationStepView;
import pl.edu.agh.fis.view.verification.VerificationStepView_;

/**
 * Created by wemstar on 2016-07-03.
 */
@EBean
public class VerificationAdapter extends BaseAdapter {

    private List<VerificationStepDTO> verificationSteps;

    @RootContext
    Context context;

    private VerificationActivity activity;

    @Override
    public int getCount() {
        return verificationSteps.size();
    }

    @Override
    public VerificationStepDTO getItem(int position) {
        return verificationSteps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VerificationStepView verificationStepView;
        if (convertView == null) {
            verificationStepView = VerificationStepView_.build(context);
        } else {
            verificationStepView = (VerificationStepView) convertView;
        }

        verificationStepView.setVerificationStep(getItem(position), position, activity);

        return verificationStepView;
    }

    public void setVerificationSteps(List<VerificationStepDTO> verificationSteps) {
        this.verificationSteps = verificationSteps;
    }

    public void setActivity(VerificationActivity activity) {
        this.activity = activity;
    }
}
