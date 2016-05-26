package pl.edu.agh.fis.adapter.list.chapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.view.document.chapter.ChapterItemEditView;
import pl.edu.agh.fis.view.document.chapter.ChapterItemEditView_;

/**
 * Created by wemstar on 2016-05-18.
 */
@EBean
public class ChapterListAdapter extends BaseAdapter {

    DocumentDTO document;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return document.chapters.size();
    }

    @Override
    public ChapterDTO getItem(int position) {
        return document.chapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChapterItemEditView chapterItemEditView;
        if (convertView == null) {
            chapterItemEditView = ChapterItemEditView_.build(context);
        } else {
            chapterItemEditView = (ChapterItemEditView) convertView;
        }

        chapterItemEditView.bindChapter(getItem(position));

        return chapterItemEditView;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
}
