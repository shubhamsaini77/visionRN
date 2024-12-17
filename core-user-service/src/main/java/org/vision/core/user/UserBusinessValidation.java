package org.vision.core.user;

import org.vision.core.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserBusinessValidation {

    private static Logger logger = LoggerFactory.getLogger(UserBusinessValidation.class);


    public CommonResponse businessValidateCreateUser(User user, String locale) throws Exception {
        CommonResponse commonResponse = new CommonResponse();
        List<String> errorList = businessValidateUser(user, locale);
        logger.info("-------IN businessValidateCreateUser-----------Error Size : {} ",errorList.size());
        if(!errorList.isEmpty()) {
            commonResponse.setResponseMessage("FAILURE");
            commonResponse.setResponseObject(errorList);
            commonResponse.setResponseCode("400");
            commonResponse.setValidRequest(false);
        }
        return commonResponse;
    }

    private List<String> businessValidateUser(User user, String locale) throws Exception {

        List<String> errorList = new ArrayList<>();
        UserDataAccess userDataAccess = new UserDataAccess();
        User userDB = userDataAccess.getUserByUsername(user.getUserName());
        if(userDB != null) {
            errorList.add("User already exists with this username");
        }

        User userByMobileNumber = userDataAccess.getUserByMobileNumber(user.getMobileNumber());
        if(userByMobileNumber != null) {
            errorList.add("User already exists with this Mobile Number");
        }

        User userByEmail = userDataAccess.getUserByEmail(user.getEmail());
        if(userByEmail != null) {
            errorList.add("User already exists with this Email");
        }

        return errorList;
    }
}
