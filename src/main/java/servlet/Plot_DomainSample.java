package servlet;

/**
 * Created by 红印 on 2016/9/22.
 */
import data.*;
import util.MyArrayList;
import util.SQLMan;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
@WebServlet("/plot_DomainSample")
public class Plot_DomainSample extends MyServlet{
    @Override
    protected String doMyGet(HttpServletRequest req) {

        return "";

    }
    public static MyArrayList<Name> plot_DomainSample(String str) {
        MyArrayList<Name> sample = null;
        String sql =null;
        sql="SELECT S_Sha256 as name FROM sample WHERE S_ID in (select S_ID from sd where D_ID=?)";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            sample = (MyArrayList<Name>) MyBean.newInstanceArray(rs, Name.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sample;
    }
}
