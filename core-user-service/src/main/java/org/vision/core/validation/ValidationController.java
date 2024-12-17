package org.vision.core.validation;

import org.vision.core.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/validation")
public class ValidationController {

    private static Logger logger = LoggerFactory.getLogger(ValidationController.class);

    @PostMapping("/insert")
    public CommonResponse insertIntoValidation(@RequestBody Validation validation, @RequestHeader("locale") String locale) throws Exception{

        CommonResponse commonResponse = new CommonResponse();
        ValidationHelper validationHelper = new ValidationHelper();
        try {
            if(validation == null) {
                logger.info("------------------------Validation Details are Null------------------------");
                commonResponse.setResponseMessage("Failure");
                commonResponse.setValidRequest(false);
                commonResponse.setResponseCode("400");

                return commonResponse;
            }
            validation = validationHelper.insertIntoValidation(validation, locale);
            commonResponse.setResponseObject(validation);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return commonResponse;
    }
}
