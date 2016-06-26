package pl.edu.agh.fis.adapter.list.document;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.fis.R;
import pl.edu.agh.fis.commons.HeaderType;
import pl.edu.agh.fis.dto.application.ApplicationDTO;
import pl.edu.agh.fis.dto.application.template.ApplicationTemplateDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.rest.application.ApplicationClient;
import pl.edu.agh.fis.rest.application.tempalte.ApplicationTemplateClient;
import pl.edu.agh.fis.rest.document.DocumentClient;
import pl.edu.agh.fis.view.common.DocumentEntryItemView;
import pl.edu.agh.fis.view.common.DocumentEntryItemView_;
import pl.edu.agh.fis.view.common.StaticTextItemView;
import pl.edu.agh.fis.view.common.StaticTextItemView_;

/**
 * Created by wemstar on 2016-05-07.
 */
@EBean
public class DocumentListAdapter extends BaseExpandableListAdapter {

    ArrayList<DocumentDTO> documents =  new ArrayList<>();
    ArrayList<ApplicationDTO> application = new ArrayList<>();
    ArrayList<ApplicationTemplateDTO> template = new ArrayList<>();

    @RestService
    DocumentClient documentClient;

    @RestService
    ApplicationClient applicationClient;

    @RestService
    ApplicationTemplateClient templateClient;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter() {
        getDocuments();
    }

    @Background
    public void getDocuments() {
        documents = new ArrayList<>(documentClient.getDocuments().getContent());
        application = new ArrayList<>(applicationClient.getApplications().getContent());
        template = new ArrayList<>(templateClient.getTemplates().getContent());
    }

    @Background
    public void deleteDocument(int position) {
        documentClient.deleteDocument(documents.get(position).id);
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch (HeaderType.getHederFromInt(groupPosition)) {
            case DOCUMENT:
                return documents.size();
            case APPLICATION:
                return application.size();
            case TEMPLATE:
                return template.size();
        }
        return 0;
    }

    @Override
    public List<?> getGroup(int groupPosition) {
        switch (HeaderType.getHederFromInt(groupPosition)) {
            case DOCUMENT:
                return documents;
            case APPLICATION:
                return application;
            case TEMPLATE:
                return template;
        }
        return new ArrayList<>();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition * childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        StaticTextItemView staticTextItemView;
        if (convertView == null) {
            staticTextItemView = StaticTextItemView_.build(context);
        } else {
            staticTextItemView = (StaticTextItemView) convertView;
        }

        switch (HeaderType.getHederFromInt(groupPosition)) {
            case DOCUMENT:
                staticTextItemView.bindLabel(context.getString(R.string.document_section_title));
                break;
            case APPLICATION:
                staticTextItemView.bindLabel(context.getString(R.string.application_section_title));
                break;
            case TEMPLATE:
                staticTextItemView.bindLabel(context.getString(R.string.template_section_title));
                break;
        }

        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        mExpandableListView.expandGroup(groupPosition);

        return staticTextItemView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        DocumentEntryItemView documentEntryItemView;
        if (convertView == null) {
            documentEntryItemView = DocumentEntryItemView_.build(context);
        } else {
            documentEntryItemView = (DocumentEntryItemView) convertView;
        }

        Object entry = getChild(groupPosition, childPosition);

        switch (HeaderType.getHederFromInt(groupPosition)) {
            case DOCUMENT:
                documentEntryItemView.bindDocument((DocumentDTO) entry);
                break;
            case APPLICATION:
                documentEntryItemView.bindApplication((ApplicationDTO) entry);
                break;
            case TEMPLATE:
                documentEntryItemView.bindTemplate((ApplicationTemplateDTO) entry);
                break;
        }

        return documentEntryItemView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
