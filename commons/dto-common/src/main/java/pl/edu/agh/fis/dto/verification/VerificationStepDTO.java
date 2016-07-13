package pl.edu.agh.fis.dto.verification;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wemstar on 2016-07-03.
 */
public class VerificationStepDTO implements Serializable {
    public String id;
    public boolean filled;
    public List<Long> allowedUserGroup;
}
