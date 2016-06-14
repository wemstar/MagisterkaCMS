package pl.edu.agh.fis.activity.application.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.application.ApplicationCreateActivity;
import pl.edu.agh.fis.activity.application.ApplicationCreateActivity_;
import pl.edu.agh.fis.adapter.list.application.template.ApplicationTemplateListAdapter;

/**
 * Created by wemstar on 2016-06-12.
 */

@EActivity(R.layout.activity_list_template)
public class ApplicationTemplateListActivity extends AppCompatActivity {

    @Bean
    ApplicationTemplateListAdapter adapter;

    @ViewById(R.id.listView)
    ListView listView;

    @AfterViews
    void initTable() {
        adapter.loadTemplates();
        listView.setAdapter(adapter);
    }

    @ItemClick(R.id.listView)
    public void itemCLicked(int position) {
        Intent intent = new Intent(this, ApplicationCreateActivity_.class);
        intent.putExtra(ApplicationCreateActivity.APPLICATION_TEMPLATE, adapter.getItem(position));
        startActivity(intent);
    }


}
