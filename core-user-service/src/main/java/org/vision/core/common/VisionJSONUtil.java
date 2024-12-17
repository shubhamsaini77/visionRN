package org.vision.core.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimeZone;

public class VisionJSONUtil {

    private static Logger logger = LoggerFactory.getLogger(VisionJSONUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    private static Gson gsonmapper = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
    static
    {
        mapper.setTimeZone(TimeZone.getDefault());
    }

    public static String createJson(Object object) {
        if(object == null) {
            return null;
        }
        String objectJson = VisionJSONUtil.gsonmapper.toJson(object);
        return objectJson;
    }
}
