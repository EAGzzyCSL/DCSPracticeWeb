package servlet;

import data.DomainRecord;
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
@WebServlet("/get_domainRecord")
public class Get_domainRecord extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_domainRecord(id).toJson();
    }
    public static MyArrayList<DomainRecord> get_domainRecord(String str) {
        MyArrayList<DomainRecord> num = null;
        String sql =null;
        sql="SELECT di.di_time,ip.IP_Info FROM di,ip where di.IP_ID=ip.IP_ID and di.D_ID=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<DomainRecord>) MyBean.newInstanceArray(rs, DomainRecord.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
