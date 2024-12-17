package org.vision.core.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VisionUtility {

    private static Logger logger = LoggerFactory.getLogger(VisionUtility.class);

    public static <k extends Comparable> String toCommaList(List<k> input) {
        return String.join(",", input.stream().map(listval -> ("'" + listval + "'")).collect(Collectors.toList()));
    }

    public static <k extends Comparable> String toCommaList(k[] input) {
        return toCommaList(Arrays.asList(input));
    }
}
