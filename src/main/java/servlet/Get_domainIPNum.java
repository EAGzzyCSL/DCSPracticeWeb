package servlet;

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
 * Created by 红印 on 2016/9/22.
 */
@WebServlet("/get_domainIPNum")
public class Get_domainIPNum extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_domainIPNum(id).toJson();
    }
    public static MyArrayList<IPNum> get_domainIPNum(String str) {
        MyArrayList<IPNum> num = null;
        String sql =null;
        sql="SELECT count(distinct AA.D_ID) as num FROM (select D_ID from di where IP_ID=?" +
               " union all select D_ID from sd where IP_ID=?) AA";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            preS.setString(2, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<IPNum>) MyBean.newInstanceArray(rs, IPNum.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
