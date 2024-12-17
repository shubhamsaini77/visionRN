package org.vision.core.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {

    private static Logger logger = LoggerFactory.getLogger(ValidationHelper.class);

    public List<Validation> getValidationsList(String module, String locale) throws Exception {

        String error = null;
        ValidationDataAccess validationDataAccess = new ValidationDataAccess();
        List<Validation> validationList = validationDataAccess.getValidations(module, locale);

        return validationList;
    }

    public String validateField(String value, String field, List<Validation> validationList) throws Exception {

        String error = null;

        for (Validation validation : validationList) {
            if (validation.getFieldNameKey().equalsIgnoreCase(field)) {

                if(validation.getMandatory() && (value == null || value.isEmpty())) {
                    error = ""+validation.getFieldDisplayName()+" is required";
                    return error;
                }

                if(validation.getRegex() != null) {
                    String regex = validation.getRegex();
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(value);
                    if(!matcher.matches()) {
                        error = "Please provide correct value for "+validation.getFieldDisplayName()+"";
                        return error;
                    }
                }

                if(validation.getMinLength() != null && validation.getMaxLength() != null) {
                    int minLength = validation.getMinLength();
                    int maxLength = validation.getMaxLength();
                    error = (value.length()<minLength) ? "" : (value.length()>maxLength) ? "" : null;
                    return error;
                }
            }
        }

        return error;
    }

    public Validation insertIntoValidation(Validation validation, String locale) throws Exception{

        ValidationDataAccess validationDataAccess = new ValidationDataAccess();
        validationDataAccess.insertIntoValidation(validation, locale);

        return validation;
    }
}
