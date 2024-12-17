package org.vision.core.common.dbUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

public class SQLErrorHandler {

    private static Logger logger = LoggerFactory.getLogger(SQLErrorHandler.class);

    public boolean errorRecoverable(SQLException e) {
        String errorMessage = e.getMessage();
        String sqlState = e.getSQLState();
        int errorCode = e.getErrorCode();

        logger.error("SQL Error :" + errorMessage);
        logger.error("SQL State is :" + sqlState);
        logger.error("Error code received :" + errorCode);

        if(StringUtils.containsWhitespace(errorMessage)) {
            logger.error("Error in transaction commit due to IO");
            return true;
        }
        return false;
    }
}
