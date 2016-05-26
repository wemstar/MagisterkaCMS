package pl.edu.agh.fis.adapter.list.paragraph;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.ParagraphDTO;
import pl.edu.agh.fis.view.document.paragraph.ParagraphItemView;
import pl.edu.agh.fis.view.document.paragraph.ParagraphItemView_;

/**
 * Created by wemstar on 2016-05-24.
 */
@EBean
public class ParagraphListAdapter extends BaseAdapter {

    ChapterDTO chapter;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return chapter.paragraphs.size();
    }

    @Override
    public ParagraphDTO getItem(int position) {
        return chapter.paragraphs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParagraphItemView chapterItemView;
        if (convertView == null) {
            chapterItemView = ParagraphItemView_.build(context);
        } else {
            chapterItemView = (ParagraphItemView) convertView;
        }

        chapterItemView.bindParagraph(getItem(position));

        return chapterItemView;
    }

    public void setChapter(ChapterDTO chapter) {
        this.chapter = chapter;
    }
}

