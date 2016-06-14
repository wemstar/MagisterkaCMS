package pl.edu.agh.fis.adapter.list.application;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.FieldDTO;
import pl.edu.agh.fis.view.application.item.ApplicationFieldItem;
import pl.edu.agh.fis.view.application.item.ApplicationFieldItem_;

/**
 * Created by wemstar on 2016-06-14.
 */
@EBean
public class ApplicationDetailsAdapter extends BaseAdapter {

    private ApplicationDTO application;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return application.fields.size();
    }

    @Override
    public FieldDTO getItem(int position) {
        return application.fields.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApplicationFieldItem applicationFieldItem;
        if (convertView == null) {
            applicationFieldItem = ApplicationFieldItem_.build(context);
        } else {
            applicationFieldItem = (ApplicationFieldItem) convertView;
        }
        applicationFieldItem.bindField(getItem(position));
        return applicationFieldItem;
    }

    public void setApplication(ApplicationDTO application) {
        this.application = application;
    }
}
