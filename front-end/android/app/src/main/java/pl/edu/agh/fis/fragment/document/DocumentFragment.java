package pl.edu.agh.fis.fragment.document;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.activity.document.details.DetailsDocumentActivity;
import pl.edu.agh.fis.activity.document.details.DetailsDocumentActivity_;
import pl.edu.agh.fis.adapter.list.document.DocumentListAdapter;


@EFragment(R.layout.fragment_document)
public class DocumentFragment extends Fragment {


    @Bean
    DocumentListAdapter documentListAdapter;

    @ViewById(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    @ViewById
    ListView listView;

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
    }

    @ItemLongClick(R.id.listView)
    void onListItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsDocumentActivity_.class);
        intent.putExtra(DetailsDocumentActivity.DOCUMENT_DETAILS_INTENT, documentListAdapter.getItem(position));
        startActivity(intent);
    }

    @UiThread
    void refreshAdapter() {
        documentListAdapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }
}
