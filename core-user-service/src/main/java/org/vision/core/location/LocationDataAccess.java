package org.vision.core.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vision.core.common.dbUtil.VisionDBUtil;
import org.vision.core.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocationDataAccess {

    private static Logger logger = LoggerFactory.getLogger(LocationDataAccess.class);

    Connection connection;
    public LocationDataAccess() throws SQLException {
        connection = VisionDBUtil.getConnection();
    }
    public Location saveLocation(String locale, Location location) throws Exception {
        String query = "INSERT INTO userlocation(id, latitude, longitude, locationTime, accuracy) VALUES('"+location.getId()+"'," +
                "'"+location.getLatitude()+"'," +
                "'"+location.getLongitude()+"'," +
                "'"+location.getLocationTime()+"'," +
                "'"+location.getAccuracy()+"')";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        logger.info("----------------------Data Added-----------------------");

        return location;
    }
}
