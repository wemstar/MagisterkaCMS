package pl.edu.agh.fis.activity.action;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.dto.activity.CommentDTO;

/**
 * Created by wemstar on 2016-06-26.
 */
@EActivity(R.layout.activity_create_action)
@OptionsMenu(R.menu.save_only_menu)
public class CreateActionActivity extends AppCompatActivity {

    public static final String ACTION_INTENT = "ACTION_INTENT";
    private ActivityDTO activityDTO;
    private ArrayAdapter<CharSequence> adapter;

    @ViewById
    Spinner actionTypeSpinner;

    @ViewById
    EditText commentEditText;

    @AfterViews
    void initViews() {
        adapter = ArrayAdapter.createFromResource(this,
                R.array.action_types, android.R.layout.simple_spinner_item);
        activityDTO = new ActivityDTO();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionTypeSpinner.setAdapter(adapter);
    }

    @OptionsItem(R.id.action_save)
    void saveDocument() {
        activityDTO.comment = new CommentDTO();
        activityDTO.comment.content = commentEditText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(ACTION_INTENT, activityDTO);
        setResult(RESULT_OK, intent);
        finish();
    }


}
