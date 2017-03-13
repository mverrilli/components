package org.talend.components.netsuite.util;

import org.talend.components.api.exception.ComponentException;
import org.talend.daikon.properties.ValidationResult;

/**
 *
 */
public abstract class ComponentExceptions {

    public static ValidationResult exceptionToValidationResult(Exception ex) {
        ValidationResult vr = null;
        if (ex instanceof ComponentException) {
            vr = ((ComponentException) ex).getValidationResult();
        }
        if (vr == null) {
            vr = new ValidationResult();
            vr.setMessage(ex.getMessage());
            vr.setStatus(ValidationResult.Result.ERROR);
        }
        return vr;
    }

    public static ComponentException asComponentExceptionWithValidationResult(Exception ex) {
        if (ex instanceof ComponentException && (((ComponentException) ex).getValidationResult() != null)) {
            return ((ComponentException) ex);
        }
        return new ComponentException(exceptionToValidationResult(ex));
    }

}
