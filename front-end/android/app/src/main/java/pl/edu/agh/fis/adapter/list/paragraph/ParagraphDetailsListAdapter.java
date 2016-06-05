package pl.edu.agh.fis.adapter.list.paragraph;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import pl.edu.agh.fis.dto.document.ParagraphDTO;
import pl.edu.agh.fis.view.document.paragraph.ParagraphDetailsItemEditView;
import pl.edu.agh.fis.view.document.paragraph.ParagraphDetailsItemEditView_;

/**
 * Created by wemstar on 2016-05-29.
 */
@EBean
public class ParagraphDetailsListAdapter  extends BaseAdapter {

    ParagraphDTO paragraph;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return paragraph.content.size();
    }

    @Override
    public String getItem(int position) {
        return paragraph.content.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParagraphDetailsItemEditView paragraphItemView;
        if (convertView == null) {
            paragraphItemView = ParagraphDetailsItemEditView_.build(context);
        } else {
            paragraphItemView = (ParagraphDetailsItemEditView) convertView;
        }

        paragraphItemView.bindParagraph(paragraph,position);

        return paragraphItemView;
    }

    public void setParagraph(ParagraphDTO paragraph) {
        this.paragraph = paragraph;
    }
}
