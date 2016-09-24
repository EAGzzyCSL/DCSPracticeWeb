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
 * Created by dell on 2016/9/22.
 */
@WebServlet("/plot_IPDomain")
public class Plot_IPDomain extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        return "";

    }
    public static MyArrayList<Name> plot_IPDomain(String str,String d_name) {
        MyArrayList<Name> num = null;
        String sql =null;
        sql="SELECT D_Name as name FROM domain where D_Name<>? and D_ID IN(select D_ID from di where IP_ID=?" +
                " union all select D_ID from sd where IP_ID=?) limit 0,50";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1,d_name);
            preS.setString(2, str);
            preS.setString(3, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<Name>) MyBean.newInstanceArray(rs, Name.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
