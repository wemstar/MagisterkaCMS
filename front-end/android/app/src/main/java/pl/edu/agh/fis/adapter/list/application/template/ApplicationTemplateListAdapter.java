package pl.edu.agh.fis.adapter.list.application.template;

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

import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.rest.application.tempalte.ApplicationTemplateClient;
import pl.edu.agh.fis.view.application.template.item.ApplicationTemplateListItem;
import pl.edu.agh.fis.view.application.template.item.ApplicationTemplateListItem_;

/**
 * Created by wemstar on 2016-06-12.
 */
@EBean
public class ApplicationTemplateListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    @RestService
    ApplicationTemplateClient templateClient;

    private List<ApplicationTemplateDTO> templates = new ArrayList<>();

    @Background
    public void loadTemplates() {
        templates = new ArrayList<>(templateClient.getTemplates().getContent());
        reload();
    }

    @UiThread
    public void reload() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return templates.size();
    }

    @Override
    public ApplicationTemplateDTO getItem(int position) {
        return templates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApplicationTemplateListItem applicationTemplateListItemView;
        if (convertView == null) {
            applicationTemplateListItemView = ApplicationTemplateListItem_.build(context);
        } else {
            applicationTemplateListItemView = (ApplicationTemplateListItem) convertView;
        }
        applicationTemplateListItemView.bindTemplate(getItem(position));
        return applicationTemplateListItemView;
    }
}
