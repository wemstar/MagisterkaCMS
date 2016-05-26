package pl.edu.agh.fis.adapter.list.chapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.view.document.chapter.ChapterItemView;
import pl.edu.agh.fis.view.document.chapter.ChapterItemView_;

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
        ChapterItemView chapterItemView;
        if (convertView == null) {
            chapterItemView = ChapterItemView_.build(context);
        } else {
            chapterItemView = (ChapterItemView) convertView;
        }

        chapterItemView.bindChapter(getItem(position));

        return chapterItemView;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }
}
