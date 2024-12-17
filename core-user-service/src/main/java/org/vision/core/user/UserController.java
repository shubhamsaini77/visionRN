package org.vision.core.user;


import org.vision.core.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public CommonResponse createUser(@RequestBody User user, @RequestHeader("locale") String locale)
        throws Exception
    {
        UserHelper userHelper = new UserHelper();
        CommonResponse commonResponse = new CommonResponse();

        try
        {
            commonResponse = new UserFieldValidation().validateCreateUser(user, locale);
            if (!commonResponse.isValidRequest()) {
                return commonResponse;
            }

            commonResponse = new UserBusinessValidation().businessValidateCreateUser(user, locale);
            if (!commonResponse.isValidRequest()) {
                return commonResponse;
            }

            user = userHelper.createUser(user, locale);
            commonResponse.setResponseObject(user);

        } catch (Throwable e)
        {
            e.printStackTrace();
        }
        return commonResponse;
    }

    @GetMapping("/view/{id}")
    public CommonResponse getUserById(@PathVariable("id") String id, @RequestHeader("locale") String locale) throws Exception {

        UserDataAccess userDataAccess = new UserDataAccess();
        CommonResponse commonResponse = new CommonResponse();

        try {
            User user = userDataAccess.getUserById(id);
            commonResponse.setResponseObject(user);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return commonResponse;
    }

}
