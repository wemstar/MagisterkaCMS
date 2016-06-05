package pl.edu.agh.fis.adapter.list.document.element;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import pl.edu.agh.fis.dto.document.ChapterDTO;
import pl.edu.agh.fis.dto.document.DocumentDTO;
import pl.edu.agh.fis.dto.document.ParagraphDTO;
import pl.edu.agh.fis.view.document.chapter.ChapterItemEditView;
import pl.edu.agh.fis.view.document.chapter.ChapterItemEditView_;
import pl.edu.agh.fis.view.document.chapter.ChapterItemView;
import pl.edu.agh.fis.view.document.chapter.ChapterItemView_;
import pl.edu.agh.fis.view.document.paragraph.ParagraphItemEditView;
import pl.edu.agh.fis.view.document.paragraph.ParagraphItemEditView_;

/**
 * Created by wemstar on 2016-05-08.
 */
@EBean
public class DocumentElementAdapter extends BaseExpandableListAdapter {

    private DocumentDTO document;

    public DocumentDTO getDocument() {
        return document;
    }

    public void setDocument(DocumentDTO document) {
        this.document = document;
    }

    @RootContext
    Context context;

    @Override
    public int getGroupCount() {
        return document.chapters.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<ParagraphDTO> paragraphs = document.chapters.get(groupPosition).paragraphs;
        return paragraphs == null ? 0 : paragraphs.size();
    }

    @Override
    public ChapterDTO getGroup(int groupPosition) {
        return document.chapters.get(groupPosition);
    }

    @Override
    public ParagraphDTO getChild(int groupPosition, int childPosition) {
        return document.chapters.get(groupPosition).paragraphs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition * groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ChapterItemView chapterItemView;
        if (convertView == null) {
            chapterItemView = ChapterItemView_.build(context);
        } else {
            chapterItemView = (ChapterItemView) convertView;
        }
        chapterItemView.bindChapter(getGroup(groupPosition));

        return chapterItemView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ParagraphItemEditView paragraphItemEditView;
        if (convertView == null) {
            paragraphItemEditView = ParagraphItemEditView_.build(context);
        } else {
            paragraphItemEditView = (ParagraphItemEditView) convertView;
        }
        paragraphItemEditView.bindParagraph(getChild(groupPosition, childPosition));
        return paragraphItemEditView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
