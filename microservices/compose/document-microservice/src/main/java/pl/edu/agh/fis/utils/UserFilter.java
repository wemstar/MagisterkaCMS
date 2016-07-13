package pl.edu.agh.fis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.fis.model.application.ApplicationEntity;
import pl.edu.agh.fis.model.application.template.ApplicationTemplateEntity;
import pl.edu.agh.fis.model.document.DocumentEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by wemstar on 2016-07-03.
 */
@Component
public class UserFilter {

    @Autowired
    UserCommonUtils userCommonUtils;

    public List<ApplicationEntity> filterApplications(Iterable<ApplicationEntity> allEntity) {
        Long userId = userCommonUtils.getCurrentUserId();
        return StreamSupport.stream(allEntity.spliterator(),false).filter(p -> p.author == userId).collect(Collectors.toList());
    }

    public List<ApplicationTemplateEntity> filterTemplates(Iterable<ApplicationTemplateEntity> allEntity) {
        Long userId = userCommonUtils.getCurrentUserId();
        return StreamSupport.stream(allEntity.spliterator(),false).filter(p -> p.author == userId).collect(Collectors.toList());
    }

    public List<DocumentEntity> filterDocuments(Iterable<DocumentEntity> allEntity) {
        Long userId = userCommonUtils.getCurrentUserId();
        return StreamSupport.stream(allEntity.spliterator(),false).filter(p -> p.author == userId).collect(Collectors.toList());
    }
}
