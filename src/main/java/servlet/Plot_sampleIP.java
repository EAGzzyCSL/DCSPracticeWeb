package servlet;

import data.*;
import util.MyArrayList;
import util.SQLMan;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 红印 on 2016/9/22.
 */
@WebServlet("/plot_sampleIP")
public class Plot_sampleIP extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {

        return "";
    }
    public static MyArrayList<Name> plot_sampleIP(String str) {
        MyArrayList<Name> num = null;
        String sql =null;
        sql="select distinct IP_Info as name from newsd where S_ID=? and IP_Info<>?";

        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            preS.setString(2,"");
            ResultSet rs = preS.executeQuery();

            num = (MyArrayList<Name>) MyBean.newInstanceArray(rs, Name.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
