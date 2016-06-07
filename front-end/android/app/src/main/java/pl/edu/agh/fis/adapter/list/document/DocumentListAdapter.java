package pl.edu.agh.fis.adapter.list.document;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.rest.document.DocumentClient;
import pl.edu.agh.fis.view.document.list.item.DocumentItemView;
import pl.edu.agh.fis.view.document.list.item.DocumentItemView_;

/**
 * Created by wemstar on 2016-05-07.
 */
@EBean
public class DocumentListAdapter extends BaseAdapter {

    ArrayList<DocumentDTO> documents =  new ArrayList<>();

    @RestService
    DocumentClient documentClient;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        getDocuments();
    }

    @Background
    public void getDocuments() {
        documents = new ArrayList<DocumentDTO>(documentClient.getDocuments().getContent());
    }

    @Override
    public int getCount() {
        return documents.size();
    }

    @Override
    public DocumentDTO getItem(int position) {
        return documents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DocumentItemView documentItemView;
        if (convertView == null) {
            documentItemView = DocumentItemView_.build(context);
        } else {
            documentItemView = (DocumentItemView) convertView;
        }

        documentItemView.bind(getItem(position));

        return documentItemView;
    }

    @Background
    public void deleteDocument(int position) {
        documentClient.deleteDocument(documents.get(position).id);
    }
}
