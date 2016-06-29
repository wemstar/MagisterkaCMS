package pl.edu.agh.fis.document;

import pl.edu.agh.fis.activity.ActivityEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by wemstar on 2016-04-13.
 */
public class DocumentEntity {

    public String id;
    public String title;
    public Date date;
    public Long author;
    public List<ChapterEntity> chapters;
    public List<ActivityEntity> activities;
}
