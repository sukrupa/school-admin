package org.sukrupa.app.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

/**
 * Created by IntelliJ IDEA.
 * User: juanibiapina
 * Date: 2/15/11
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidatorUtils {
    public static boolean missingRequiredField(String fieldName, Errors errors) {
        Object value = errors.getFieldValue(fieldName);
        return value == null || !StringUtils.hasText(value.toString());
    }

    public static boolean missingAnyRequiredFields(Errors errors, String... fields) {
        for (String fieldName: fields) {
            if (missingRequiredField(fieldName, errors)) {
                return true;
            }
        }
        return false;
    }
}
