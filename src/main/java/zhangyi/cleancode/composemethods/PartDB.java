package zhangyi.cleancode.composemethods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartDB extends JdbcTemplate {
    private List<Part> partList = new ArrayList<Part>();

    @Override
    protected void populateEntities(ResultSet rs) throws SQLException {
        // populate parts
        while (rs.next()) {
            Part p = new Part();
            p.setName(rs.getString("name"));
            p.setBrand(rs.getString("brand"));
            p.setRetailPrice(rs.getDouble("retail_price"));
            partList.add(p);
        }
    }

    @Override
    protected String getSql() {
        return "select * from part";
    }

}