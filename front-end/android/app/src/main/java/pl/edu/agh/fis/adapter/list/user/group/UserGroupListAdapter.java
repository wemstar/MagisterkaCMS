package pl.edu.agh.fis.adapter.list.user.group;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.dto.user.UserGroupDTO;
import pl.edu.agh.fis.rest.user.group.UserGroupClient;
import pl.edu.agh.fis.view.user.group.UserGroupItemView;
import pl.edu.agh.fis.view.user.group.UserGroupItemView_;

/**
 * Created by wemstar on 2016-07-05.
 */
@EBean
public class UserGroupListAdapter extends BaseAdapter {

    @RootContext
    Context context;
    private List<Long> selectedGroup;
    private List<UserGroupDTO> allUserGroups = new ArrayList<>();

    @RestService
    UserGroupClient userGroupClient;

    @UiThread
    void reload() {
        notifyDataSetChanged();
    }


    @Background
    public void loadGroups() {
        allUserGroups = new ArrayList<>(userGroupClient.getUserGroups().getContent());
        reload();
    }

    @Override
    public int getCount() {
        return allUserGroups.size();
    }

    @Override
    public UserGroupDTO getItem(int position) {
        return allUserGroups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserGroupItemView userGroupItemView;
        if (convertView == null) {
            userGroupItemView = UserGroupItemView_.build(context);
        } else {
            userGroupItemView = (UserGroupItemView) convertView;
        }
        userGroupItemView.setSelected(false);
        userGroupItemView.setSelected(selectedGroup.contains(getItem(position).getId()));
        userGroupItemView.bindString(getItem(position).getId().toString());
        return userGroupItemView;
    }

    public void setSelectedGroup(List<Long> selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
}
