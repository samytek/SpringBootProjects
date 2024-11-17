package com.example.SpringBootBatchProcessingJPA.Utils;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class CommmonUtil {

    private static final String NULLVALUE = "null";

    public static boolean isNullOrEmpty(final Object object) {
        if (object == null) {
            return true;
        }
        if (object.equals(NULLVALUE)) {
            return true;
        }
        if (object instanceof String) {
            return StringUtils.isBlank((String) object);
        }
        if (object instanceof List) {
            return ((List) object).isEmpty();
        }
        if (object instanceof Map) {
            return ((Map) object).size() <= 0;
        }
        if (object instanceof ArrayNode) {
            return ((ArrayNode) object).size() == 0;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotNullAndNotEmpty(final Object object) {
        if (object != null) {
            if (object instanceof List) {
                return !((List) object).isEmpty();
            }
            if (object instanceof String) {
                if (StringUtils.equals((String) object, NULLVALUE)) {
                    return false;
                } else if (StringUtils.isBlank((String) object)) {
                    return false;
                } else {
                    return true;
                }
            }
            if (object instanceof Map) {
                return ((Map) object).size() > 0;
            }
            if (object instanceof ArrayNode) {
                return ((ArrayNode) object).size() > 0;
            }
            return true;
        }
        return false;
    }
}
