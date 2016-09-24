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
@WebServlet("/plot_IPSample")
public class Plot_IPSample extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return plot_IPSample(id).toJson();
    }
    public static MyArrayList<Name> plot_IPSample(String str) {
        MyArrayList<Name> num = null;
        String sql =null;
        sql="SELECT S_Sha256 as name FROM sample where S_ID IN(SELECT S_ID FROM sd where IP_ID=?)";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<Name>) MyBean.newInstanceArray(rs, Name.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
