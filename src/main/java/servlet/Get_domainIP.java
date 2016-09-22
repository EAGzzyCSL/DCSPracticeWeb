package servlet;

/**
 * Created by dell on 2016/9/22.
 */
import data.DomainIP;
import data.ID;
import data.MyBean;
import data.Person;
import util.MyArrayList;
import util.SQLMan;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
@WebServlet("/get_domainIP")
public class Get_domainIP extends MyServlet {
    @Override
    protected String doMyGet(HttpServletRequest req) {
        String domainIP = "";
        if (req.getParameter("domain") != null)
            domainIP = req.getParameter("domain");
        return get_domainIP(domainIP).toJson();
    }

    public static MyArrayList<DomainIP> get_domainIP(String str) {
        MyArrayList<DomainIP> domainIP = null;
        String sql_domainIP = null;
        int id=0;
        sql_domainIP = "SELECT IP_ID FROM di where D_ID=? ORDER by DI_Time DESC limit 1";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql_domainIP);
           preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            if(rs.next())
                id=rs.getInt("IP_ID");
            sql_domainIP = "SELECT IP_Info FROM ip where IP_ID=?";
            preS = SQLMan.getConnection().prepareStatement(
                    sql_domainIP);
            preS.setInt(1, id);
            rs = preS.executeQuery();
            domainIP = (MyArrayList<DomainIP>) MyBean.newInstanceArray(rs, DomainIP.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return domainIP;

    }

}

