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
@WebServlet("/get_domainSample")
public class Get_domainSample extends MyServlet{
    @Override
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_domainSample(id).toJson();

    }
    public static MyArrayList<DomainSample> get_domainSample(String str) {
        MyArrayList<DomainSample> sample = null;
        String sql =null;
        sql="SELECT S_Sha256 FROM sample WHERE S_ID in (select S_ID from sd where D_ID=?)";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            sample = (MyArrayList<DomainSample>) MyBean.newInstanceArray(rs, DomainSample.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sample;
    }
}
