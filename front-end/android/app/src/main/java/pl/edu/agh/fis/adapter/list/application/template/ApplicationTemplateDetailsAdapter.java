package pl.edu.agh.fis.adapter.list.application.template;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;
import pl.edu.agh.fis.view.application.template.item.ApplicationTemplateItem;
import pl.edu.agh.fis.view.application.template.item.ApplicationTemplateItem_;

/**
 * Created by wemstar on 2016-06-07.
 */
@EBean
public class ApplicationTemplateDetailsAdapter extends BaseAdapter {
    private ApplicationTemplateDTO template;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return template.fields.size();
    }

    @Override
    public TemplateFieldsDTO getItem(int position) {
        return template.fields.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApplicationTemplateItem applicationTemplateItemView;
        if (convertView == null) {
            applicationTemplateItemView = ApplicationTemplateItem_.build(context);
        } else {
            applicationTemplateItemView = (ApplicationTemplateItem) convertView;
        }

        return applicationTemplateItemView;
    }

    public void setTemplate(ApplicationTemplateDTO template) {
        this.template = template;
    }
}
