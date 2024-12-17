package org.vision.core.user;

import org.vision.core.common.AllEnum;
import org.vision.core.common.CommonResponse;
import org.vision.core.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vision.core.validation.ValidationHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFieldValidation {

    private static Logger logger = LoggerFactory.getLogger(UserFieldValidation.class);

    public CommonResponse validateCreateUser(User user, String locale) throws Exception{

        CommonResponse commonResponse = new CommonResponse();
        Map<String, String> errors = validateUser(user, locale);
        logger.info("Error Size : {} ",errors.size());
        if(errors.size() > 0) {
            commonResponse.setResponseMessage("FAILURE");
            commonResponse.setResponseObject(errors);
            commonResponse.setResponseCode("400");
            commonResponse.setValidRequest(false);
        }
        return commonResponse;
    }
    public Map<String, String> validateUser(User user, String locale) throws Exception{

        String module = AllEnum.Modules.user.name();
        Map<String, String> hashmap = new HashMap<>();
        ValidationHelper validationHelper = new ValidationHelper();
        String errorField = null;

        List<Validation> validationList = validationHelper.getValidationsList(module, locale);

        errorField = validationHelper.validateField(user.getId(), "id", validationList);
        if (errorField != null) {
            hashmap.put("id", errorField);
        }

        errorField = validationHelper.validateField(user.getLastName(), "lastName", validationList);
        if (errorField != null) {
            hashmap.put("lastName", errorField);
        }

        errorField = validationHelper.validateField(user.getMobileNumber(), "mobileNumber", validationList);
        if (errorField != null) {
            hashmap.put("mobileNumber", errorField);
        }

        return hashmap;
    }
}
