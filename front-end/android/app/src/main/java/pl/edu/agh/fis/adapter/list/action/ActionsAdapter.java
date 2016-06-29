package pl.edu.agh.fis.adapter.list.action;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.dto.activity.ActivityDTO;
import pl.edu.agh.fis.view.action.ActionItemView;
import pl.edu.agh.fis.view.action.ActionItemView_;

/**
 * Created by wemstar on 2016-06-22.
 */
@EBean
public class ActionsAdapter extends BaseAdapter {
    private List<ActivityDTO> actions;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return actions.size();
    }

    @Override
    public ActivityDTO getItem(int position) {
        return actions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActionItemView actionItemView;
        if (convertView == null) {
            actionItemView = ActionItemView_.build(context);
        } else {
            actionItemView = (ActionItemView) convertView;
        }
        actionItemView.bindField(getItem(position));
        return actionItemView;
    }

    public void setActions(List<ActivityDTO> actions) {
        this.actions = actions;
        if (this.actions == null) {
            this.actions = new ArrayList<>();
        }
    }

    public List<ActivityDTO> getActions() {
        return actions;
    }
}
