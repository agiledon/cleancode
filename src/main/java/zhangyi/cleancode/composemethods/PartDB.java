package zhangyi.cleancode.composemethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartDB {
    private static final String DRIVER_CLASS = "";
    private static final String DB_URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static final String SQL_SELECT_PARTS = "select * from part";
    private List<Part> partList = new ArrayList<Part>();

    public void populate() throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();
            ResultSet rs = getResultSet(connection);
            while (rs.next()) {
                populateParts(rs);
            }
        } finally {
            connection.close();
        }
    }

    private void populateParts(ResultSet rs) throws SQLException {
        Part p = new Part();
        p.setName(rs.getString("name"));
        p.setBrand(rs.getString("brand"));
        p.setRetailPrice(rs.getDouble("retail_price"));
        partList.add(p);
    }

    private ResultSet getResultSet(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(SQL_SELECT_PARTS);
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}