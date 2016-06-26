package pl.edu.agh.fis.adapter.list.application;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.FieldDTO;
import pl.edu.agh.fis.view.application.item.ApplicationFiledStaticItem;
import pl.edu.agh.fis.view.application.item.ApplicationFiledStaticItem_;

/**
 * Created by wemstar on 2016-06-22.
 */
@EBean
public class ApplicationFieldsAdapter extends BaseAdapter {
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
        ApplicationFiledStaticItem applicationStaticFieldItem;
        if (convertView == null) {
            applicationStaticFieldItem = ApplicationFiledStaticItem_.build(context);
        } else {
            applicationStaticFieldItem = (ApplicationFiledStaticItem) convertView;
        }
        applicationStaticFieldItem.bindField(getItem(position));
        return applicationStaticFieldItem;
    }

    public void setApplication(ApplicationDTO application) {
        this.application = application;
    }
}
