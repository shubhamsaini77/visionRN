package org.vision.core.validation;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vision.core.common.dbUtil.VisionDBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ValidationDataAccess {

    private static Logger logger = LoggerFactory.getLogger(ValidationDataAccess.class);

    public List<Validation> getValidations(String module, String locale) throws Exception {

        Connection connection = VisionDBUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<Validation>> resultSetHandler = new BeanListHandler<Validation>(Validation.class);

        String selectQuery = "select * from validation v where module = ?";
        List<Validation> validationList = queryRunner.query(connection, selectQuery, resultSetHandler, "user");
        logger.info("----------Validation : {}-----------",validationList);
        return validationList;
    }

    public void insertIntoValidation(Validation validation, String locale) throws SQLException {

        Connection connection = VisionDBUtil.getConnection();
        String query = "INSERT INTO validation(id, module, fieldNameKey, fieldDisplayName) VALUES('"+validation.getId()+"','"+validation.getModule()+"','"+validation.getFieldNameKey()+"','"+validation.getFieldDisplayName()+"')";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            logger.info("----------------------Data Added-----------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
