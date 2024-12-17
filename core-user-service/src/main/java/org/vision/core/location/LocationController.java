package org.vision.core.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.vision.core.common.CommonResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private static Logger logger = LoggerFactory.getLogger(LocationController.class);

    @GetMapping("/getCurrentLocation")
    public CommonResponse getCurrentLocation(@RequestHeader("locale") String locale) throws Exception {

        LocationHelper locationHelper = new LocationHelper();
        CommonResponse commonResponse = new CommonResponse();

        try {
            Location location = locationHelper.getCurrentLocation(locale);
            commonResponse.setResponseObject(location);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return commonResponse;
    }

    @PostMapping("/saveLocation")
    public CommonResponse saveLocation(@RequestBody Location location, @RequestHeader("locale") String locale) throws Exception {

        LocationHelper locationHelper = new LocationHelper();
        CommonResponse commonResponse = new CommonResponse();

        try {
            commonResponse = locationHelper.saveLocation(locale, location);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return commonResponse;
    }
}
