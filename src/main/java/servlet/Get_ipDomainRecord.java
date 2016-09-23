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
@WebServlet("/get_ipDomainRecord")
public class Get_ipDomainRecord extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_ipDomainRecord(id).toJson();
    }
    public static MyArrayList<IPDomainRecord> get_ipDomainRecord(String str) {
        MyArrayList<IPDomainRecord> num = null;
        String sql =null;
        sql="select  di.DI_Time,domain.D_Name from di,domain where di.IP_ID=? and di.D_ID=domain.D_ID";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();

            num = (MyArrayList<IPDomainRecord>) MyBean.newInstanceArray(rs, IPDomainRecord.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
