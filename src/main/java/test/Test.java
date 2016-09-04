package test;

import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
// 一个javaBean的demo
public class Test implements Serializable {
    public Test() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/test").toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "no connection~~~";
    }
}
