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
@WebServlet("/get_mailboxDomain")
public class Plot_mailboxDomain extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {

        return "";
    }
    public static MyArrayList<Name> get_mailboxDomain(String str,String d_name) {
        MyArrayList<Name> num = null;
        String sql =null;
        sql="SELECT D_Name as name FROM domain where D_ID in (select D_ID from md where " +
                "M_ID in (select M_ID from mailbox where Mailbox=?)) and D_Name<>?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            preS.setString(2, d_name);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<Name>) MyBean.newInstanceArray(rs, Name.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
