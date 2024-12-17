package org.vision.core.common.dbUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QueryHolder {

    private static Logger logger = LoggerFactory.getLogger(QueryHolder.class);

    private String query;
    private List<Object> parameters;
    private Object[] parameterArray;

    public String getQuery() { return query; }

    public void setQuery(String query) { this.query = query; }

    public Object[] getParameters() {
        if (parameters != null) {
            return parameters.toArray();
        }
        if (parameterArray != null) {
            return parameterArray;
        }
        return null;
    }

    public void setParameters(List<Object> parameters) { this.parameters = parameters; }
    public void setParameterArray(Object[] parameterArray) { this.parameterArray = parameterArray; }
}
