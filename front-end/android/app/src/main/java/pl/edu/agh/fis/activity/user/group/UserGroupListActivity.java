package pl.edu.agh.fis.activity.user.group;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.adapter.list.user.group.UserGroupListAdapter;

/**
 * Created by wemstar on 2016-07-05.
 */
@EActivity(R.layout.activity_list_template)
@OptionsMenu(R.menu.save_only_menu)
public class UserGroupListActivity extends AppCompatActivity {

    public static final String USER_GROUP_INTENT = "USER_GROUP_INTENT";

    private List<Long> selectedUserGroup;

    @Bean
    UserGroupListAdapter adapter;

    @ViewById
    ListView listView;

    @AfterViews
    void initActivity() {
        selectedUserGroup = (List<Long>) getIntent().getSerializableExtra(USER_GROUP_INTENT);
        adapter.setSelectedGroup(selectedUserGroup);
        adapter.loadGroups();
        listView.setAdapter(adapter);
    }

    @ItemClick(R.id.listView)
    void itemClicked(int position) {
        if (selectedUserGroup.contains(adapter.getItem(position).getId()))
            selectedUserGroup.remove(adapter.getItem(position).getId());
        else
            selectedUserGroup.add(adapter.getItem(position).getId());

        adapter.notifyDataSetChanged();
    }

    @OptionsItem(R.id.action_save)
    void saveAction() {
        Intent intent = new Intent();
        intent.putExtra(USER_GROUP_INTENT, new ArrayList<>(selectedUserGroup));
        setResult(RESULT_OK, intent);
        finish();
    }
}
