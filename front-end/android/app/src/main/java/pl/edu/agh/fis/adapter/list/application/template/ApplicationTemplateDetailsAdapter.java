package pl.edu.agh.fis.adapter.list.application.template;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;

import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.application.template.TemplateFieldsDTO;

/**
 * Created by wemstar on 2016-06-07.
 */
@EBean
public class ApplicationTemplateDetailsAdapter extends BaseAdapter {
    private ApplicationTemplateDTO template;

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
        return null;
    }

    public void setTemplate(ApplicationTemplateDTO template) {
        this.template = template;
    }
}
