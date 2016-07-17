package pl.edu.agh.fis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.fis.model.application.ApplicationEntity;
import pl.edu.agh.fis.model.application.template.ApplicationTemplateEntity;
import pl.edu.agh.fis.model.document.DocumentEntity;
import pl.edu.agh.fis.model.verification.VerificationStep;
import pl.edu.agh.fis.repository.ApplicationRepository;
import pl.edu.agh.fis.repository.ApplicationTemplateRepository;
import pl.edu.agh.fis.repository.DocumentRepository;

import java.util.List;

/**
 * Created by wemstar on 2016-07-03.
 */
@Service
public class VerifyElementService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationTemplateRepository applicationTemplateRepository;

    public void verifyDocument(String id, List<String> verifyIds) {
        DocumentEntity entity = documentRepository.findOne(id);
        verify(entity.verificationSteps,verifyIds);

    }

    public void verifyApplication(String id, List<String> verifyIds) {
        ApplicationEntity entity = applicationRepository.findOne(id);
    }

    public void verifyTemplate(String id, List<String> verifyIds) {
        ApplicationTemplateEntity entity = applicationTemplateRepository.findOne(id);
        // TODO
        //verify(entity.verificationSteps,verifyIds);
    }

    private void verify(List<VerificationStep> verificationSteps, List<String> verifyIds) {
        verificationSteps.stream().filter(step -> verifyIds.contains(step.id)).forEach(step -> step.filled = true);
    }
}
