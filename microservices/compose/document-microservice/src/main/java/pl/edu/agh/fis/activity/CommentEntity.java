package pl.edu.agh.fis.activity;

/**
 * Created by wemstar on 2016-06-20.
 */
public class CommentEntity {

    public String id;
    public Long autorId;
    public String content;

    public CommentEntity(){}

    public CommentEntity(Long autorId, String content){
        this.autorId = autorId;
        this.content = content;
    }
}
