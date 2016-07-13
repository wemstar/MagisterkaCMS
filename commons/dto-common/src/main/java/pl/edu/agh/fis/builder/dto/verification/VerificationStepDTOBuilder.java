package pl.edu.agh.fis.builder.dto.verification;

import java.util.List;

import pl.edu.agh.fis.dto.verification.VerificationStepDTO;

/**
 * Created by wemstar on 2016-07-03.
 */
public class VerificationStepDTOBuilder {
    private String id;
    private boolean filled;
    private List<Long> allowedUserGroup;


    private VerificationStepDTOBuilder() {
    }

    public static VerificationStepDTOBuilder aVerificationStepDTOBuilder() {
        return new VerificationStepDTOBuilder();
    }

    public VerificationStepDTOBuilder filled(boolean filled) {
        this.filled = filled;
        return this;
    }

    public VerificationStepDTOBuilder allowedUserGroup(List<Long> allowedUserGroup) {
        this.allowedUserGroup = allowedUserGroup;
        return this;
    }

    public VerificationStepDTO build() {
        VerificationStepDTO verificationStepDTO = new VerificationStepDTO();
        verificationStepDTO.filled = filled;
        verificationStepDTO.allowedUserGroup = allowedUserGroup;
        return verificationStepDTO;
    }
}
