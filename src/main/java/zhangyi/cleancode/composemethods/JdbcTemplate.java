package zhangyi.cleancode.composemethods;

import java.sql.*;

public abstract class JdbcTemplate {
    private static final String DRIVER_CLASS = "";
    private static final String DB_URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public void populate() throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();
            ResultSet resultSet = executeQuery(connection);
            populateEntities(resultSet);
        } finally {
            connection.close();
        }
    }

    protected abstract void populateEntities(ResultSet rs) throws SQLException;

    private ResultSet executeQuery(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        return stmt.executeQuery(getSql());
    }

    protected abstract String getSql();

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
