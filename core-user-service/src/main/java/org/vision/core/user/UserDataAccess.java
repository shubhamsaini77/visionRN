package org.vision.core.user;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.vision.core.common.dbUtil.VisionDBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class UserDataAccess {

    private static Logger logger = LoggerFactory.getLogger(UserDataAccess.class);

    Connection connection;
    public UserDataAccess() throws SQLException {
        connection = VisionDBUtil.getConnection();
    }

    public User createUser(User user, String locale) throws Exception{
        String query = "INSERT INTO users(id, firstname) VALUES('"+user.getId()+"','"+user.getFirstName()+"')";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        logger.info("----------------------Data Added-----------------------");

        return user;
    }


    public User getUserByUsername(String userName) throws Exception {
        Connection connection = VisionDBUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);

        String selectQuery = "select * from users u where username = ?";
        User user = queryRunner.query(connection, selectQuery, resultSetHandler, userName);

        return user;
    }

    public User getUserByMobileNumber(String mobileNumber) throws Exception {
        Connection connection = VisionDBUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);

        String selectQuery = "select * from users u where mobilenumber = ?";
        User user = queryRunner.query(connection, selectQuery, resultSetHandler, mobileNumber);

        return user;
    }

    public User getUserByEmail(String email) throws Exception {
        Connection connection = VisionDBUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);

        String selectQuery = "select * from users u where email = ?";
        User user = queryRunner.query(connection, selectQuery, resultSetHandler, email);

        return user;
    }

    public User getUserById(String id) throws Exception {
        Connection connection = VisionDBUtil.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<User> resultSetHandler = new BeanHandler<>(User.class);

        String selectQuery = "select * from users u where id = ?";
        User user = queryRunner.query(connection, selectQuery, resultSetHandler, id);

        return user;
    }
}
