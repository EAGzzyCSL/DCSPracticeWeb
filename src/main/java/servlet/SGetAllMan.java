package servlet;

import data.MyBean;
import data.Person;
import util.MyArrayList;
import util.SQLMan;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@WebServlet("/getAllMan")
public class SGetAllMan extends MyServlet {

    @Override
    protected String doMyGet(HttpServletRequest req) {
        return getAllMan().toJson();
    }

    public static MyArrayList<Person> getAllMan() {
        MyArrayList<Person> persons = null;
        final String sql_getAllMan = "SELECT * FROM `test` WHERE `sex`=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql_getAllMan);
            preS.setInt(1, 1);
            ResultSet rs = preS.executeQuery();
            persons = (MyArrayList<Person>) MyBean.newInstanceArray(rs, Person.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
