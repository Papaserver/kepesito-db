package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> overpopulated = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "select breed from dinosaur " +
                    "where expected < actual " +
                    "order by breed";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (!overpopulated.contains(rs.getString("breed"))) {
                    overpopulated.add(rs.getString("breed"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return overpopulated;
    }
}
