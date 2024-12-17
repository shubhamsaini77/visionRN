package org.vision.core.user;

import org.vision.core.common.VisionJSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserHelper {

    private static Logger logger = LoggerFactory.getLogger(UserHelper.class);
    public User createUser(User user, String locale) throws Exception {
        UserDataAccess userDataAccess = new UserDataAccess();
        List<UserAddressDetails> userAddressDetails = user.getUserAddressDetails();
        logger.info("---------------userAddressDetails JSON: {} ----------", VisionJSONUtil.createJson(userAddressDetails.get(0)));
        user = userDataAccess.createUser(user, locale);
        return user;
    }

}
