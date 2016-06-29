package pl.edu.agh.fis.fragment.document;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.application.ApplicationDetailsActivity;
import pl.edu.agh.fis.activity.application.ApplicationDetailsActivity_;
import pl.edu.agh.fis.activity.application.template.ApplicationTemplateDetailsActivity;
import pl.edu.agh.fis.activity.application.template.ApplicationTemplateDetailsActivity_;
import pl.edu.agh.fis.activity.document.details.DetailsDocumentActivity;
import pl.edu.agh.fis.activity.document.details.DetailsDocumentActivity_;
import pl.edu.agh.fis.adapter.list.document.DocumentListAdapter;
import pl.edu.agh.fis.commons.HeaderType;


@EFragment(R.layout.fragment_document)
public class DocumentFragment extends Fragment {


    @Bean
    DocumentListAdapter documentListAdapter;

    @ViewById(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @ViewById
    ExpandableListView listView;

    @AfterViews
    void initAdapter() {
        listView.setAdapter(documentListAdapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                documentListAdapter.getDocuments();
                refreshAdapter();

            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                onListItemClick(groupPosition, childPosition);
                return true;
            }
        });
    }

    void onListItemClick(int group, int position) {
        Intent intent = null;
        switch (HeaderType.getHederFromInt(group)) {
            case DOCUMENT:
                intent = new Intent(getActivity(), DetailsDocumentActivity_.class);
                intent.putExtra(DetailsDocumentActivity.DOCUMENT_DETAILS_INTENT, (Serializable) documentListAdapter.getChild(group, position));
                break;
            case APPLICATION:
                intent = new Intent(getActivity(), ApplicationDetailsActivity_.class);
                intent.putExtra(ApplicationDetailsActivity.APPLICATION_DETAILS, (Serializable) documentListAdapter.getChild(group, position));
                break;
            case TEMPLATE:
                intent = new Intent(getActivity(), ApplicationTemplateDetailsActivity_.class);
                intent.putExtra(ApplicationTemplateDetailsActivity.TEMPLATE_INTENT, (Serializable) documentListAdapter.getChild(group, position));
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

    /*@ItemLongClick(R.id.listView)
    void onListItemLongClick(final int position) {
        CommonsAlert.showDialogBox(this.getContext(),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDocument(position);
            }
        });
    }*/

    @UiThread
    void refreshAdapter() {
        documentListAdapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

    private void deleteDocument(int position) {
        documentListAdapter.deleteDocument(position);
        documentListAdapter.getDocuments();
        refreshAdapter();
    }
}
