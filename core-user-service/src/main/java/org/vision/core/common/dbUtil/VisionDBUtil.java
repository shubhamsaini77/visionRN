package org.vision.core.common.dbUtil;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.vision.core.common.VisionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class VisionDBUtil {

    private static Logger logger = LoggerFactory.getLogger(VisionDBUtil.class);
    private static Map<String, List<Field>> allClassFieldsMap = new HashMap<String, List<Field>>();
    private long sleeptime = 5;
    private static Connection connection = null;
    public static Connection getConnection() throws SQLException {

        if(connection != null) {
            return connection;
        } else {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/visionTrack";
            String user = "root";
            String password = "root@7707";

            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                logger.info("----------------Connection created------------------");
                logger.info("-------------hashcode now: {}------------------", connection.hashCode());
            } catch (ClassNotFoundException e) {
                logger.info("-------------Error in Connection------------------");
                e.printStackTrace();
            }
        }

        return connection;
    }

    public <T> T selectAsObject(String query, Class<T> tclass, Object... parameters) throws Exception {
        List<T> list = selectAsList(query, tclass, parameters);

        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() > 1) {
            logger.error("The result contains more than one object");
        }
        return list.get(0);
    }

    private <T> List<T> selectAsList(String query, Class<T> tclass, Object[] parameters) throws Exception {

        List<T> list = null;

        QueryHolder queryHolder = prepareForInClause(query, parameters);
        query = queryHolder.getQuery();
        parameters = queryHolder.getParameters();
        try {
            Connection connection = VisionDBUtil.getConnection();

            logger.info("-------Read connection received is :" + connection);
            PreparedStatement statement = connection.prepareStatement(query);
            statement = setQueryValues(statement, parameters);

            ResultSet resultSet = statement.executeQuery();

            list = convertToObject(resultSet, tclass);
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            if(new SQLErrorHandler().errorRecoverable(e)) {
                sleep(sleeptime);
                return selectAsList(query, tclass, parameters);
            } else {
                throw new Exception();
            }
        }
        return list;

    }

    private <T> List<T> convertToObject(ResultSet resultSet, Class<T> tclass) throws Exception {

        List<Field> fields  = getAllFields(tclass);
        List<T> list = new ArrayList<>();

        while (resultSet.next()) {
            T dto = tclass.getConstructor().newInstance();

            for (Field field : fields) {
                String name = field.getName();
                String fieldType = field.getType().getSimpleName();
                try {
                    if (fieldType.equalsIgnoreCase("String")) {
                        String value = resultSet.getString(name);
                        field.set(dto, value);
                    } else if (fieldType.equalsIgnoreCase("Timestamp")) {
                        String value = resultSet.getString(name);
                        field.set(dto, value);
                    } else if (fieldType.equalsIgnoreCase("BigDecimal")) {
                        BigDecimal value = resultSet.getBigDecimal(name);
                        field.set(dto, value);
                    } else if (fieldType.equalsIgnoreCase("Integer") || fieldType.equalsIgnoreCase("Int")) {
                        Integer value = resultSet.getInt(name);
                        field.set(dto, value);
                    } else if (fieldType.equalsIgnoreCase("Boolean") || fieldType.equalsIgnoreCase("tinyint")) {
                        Boolean value = resultSet.getBoolean(name);
                        field.set(dto, value);
                    } else if (fieldType.equalsIgnoreCase("Double") || fieldType.equalsIgnoreCase("double")) {
                        Double value = resultSet.getDouble(name);
                        field.set(dto, value);
                    } else if (fieldType.equalsIgnoreCase("Long") || fieldType.equalsIgnoreCase("long")) {
                        Long value = resultSet.getLong(name);
                        field.set(dto, value);
                    } else {
                        Object value = resultSet.getObject(name);
                        field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
                    }
                } catch (Exception e) {
                    logger.info("Error in convertToObject {}, {}", tclass.getName(), e.getMessage());
                }
            }
            list.add(dto);
        }
        return list;
    }

    private List<Field> getAllFields(Class<?> type) {
        List<Field> fields = allClassFieldsMap.get(type.getSimpleName());
        if(fields != null) {
            return fields;
        }
        fields = new ArrayList<Field>();
        fields = getAllFields(fields, type);

        makeAccessible(fields);

        allClassFieldsMap.put(type.getSimpleName(), fields);

        return fields;
    }

    private List<Field> getAllFields(List<Field> fields, Class<?> type) {

        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if(type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }

    private PreparedStatement setQueryValues(PreparedStatement statement, Object[] parameters) throws Exception {
        for(int i = 0; i < parameters.length; i++) {
            Object object = parameters[i];
            if (object == null) {
                statement.setObject(i = 1, object);
                continue;
            }
            String simpleName = object.getClass().getSimpleName();

            if("String".equals(simpleName)) {
                statement.setString(i + 1, object.toString());
            } else if ("Timestamp".equals(simpleName)) {
                statement.setTimestamp(i + 1, (Timestamp) object);
            } else if ("BigDecimal".equals(simpleName)) {
                statement.setBigDecimal(i + 1, (BigDecimal) object);
            } else {
                statement.setObject(i + 1, (Timestamp) object);
            }
        }
        return statement;
    }

    private void sleep(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private QueryHolder prepareForInClause(String query, Object[] parameters) {
        QueryHolder queryHolder = new QueryHolder();
        for(int i = 0; i<parameters.length; i++) {
            Object object = parameters[i];

            String simpleName = object.getClass().getSimpleName();
            if(simpleName.equals("String[]")) {
                String inList = VisionUtility.toCommaList((String[]) object);
                parameters = buildInQuery(query, parameters, queryHolder, i, inList);
                return queryHolder;
            } else if (simpleName.equals("ArrayList")) {
                String inList = VisionUtility.toCommaList((ArrayList<String>) object);
                parameters = buildInQuery(query, parameters, queryHolder, i, inList);
                return queryHolder;
            }
        }
        queryHolder.setParameterArray(parameters);
        queryHolder.setQuery(query);
        return queryHolder;
    }

    private void makeAccessible(List<Field> fields) {
        for (Field field : fields) {
            field.setAccessible(true);
        }
    }

    private Object[] buildInQuery(String query, Object[] parameters, QueryHolder queryHolder, int occuranceNumber, String inList) {
        int index = StringUtils.ordinalIndexOf(query, "?", occuranceNumber + 1);
        String firstPart = query.substring(0, index);
        String secondPart = query.substring(index + 1);
        String finalQuery = firstPart + inList + secondPart;
        parameters = ArrayUtils.remove(parameters, occuranceNumber);
        queryHolder.setQuery(finalQuery);
        queryHolder.setParameterArray(parameters);
        return parameters;
    }
}
