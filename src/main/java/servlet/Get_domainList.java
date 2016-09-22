package servlet;

import data.DomainList;
import data.DomainSample;
import data.IPNum;
import data.MyBean;
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
@WebServlet("/get_domainList")
public class Get_domainList extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_domainList(id).toJson();
    }
    public static MyArrayList<DomainList> get_domainList(String str) {
        MyArrayList<DomainList> num = null;
        String sql =null;
        sql="SELECT D_Name FROM domain where D_ID IN(select D_ID from di where IP_ID=?" +
                " union all select D_ID from sd where IP_ID=?) ";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            preS.setString(2, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<DomainList>) MyBean.newInstanceArray(rs, DomainList.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
