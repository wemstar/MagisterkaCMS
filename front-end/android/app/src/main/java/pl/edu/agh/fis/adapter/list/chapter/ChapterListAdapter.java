package pl.edu.agh.fis.adapter.list.chapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import pl.edu.agh.fis.dto.document.DocumentDTO;

/**
 * Created by wemstar on 2016-05-18.
 */
public class ChapterListAdapter extends BaseAdapter {

    DocumentDTO document;

    @Override
    public int getCount() {
        return document.chapters.size();
    }

    @Override
    public Object getItem(int position) {
        return document.chapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
}
