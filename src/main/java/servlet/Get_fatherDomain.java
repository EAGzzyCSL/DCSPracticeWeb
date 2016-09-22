package servlet;

import data.ChildrenDomain;
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
@WebServlet("/get_fatherDomain")
public class Get_fatherDomain extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_fatherDomain(id).toJson();
    }
    public static MyArrayList<ChildrenDomain> get_fatherDomain(String str) {
        MyArrayList<ChildrenDomain> num = null;
        String sql =null;
        sql="SELECT D_Name FROM domain where D_ID in (select F_ID from dd where C_ID=?)";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<ChildrenDomain>) MyBean.newInstanceArray(rs, ChildrenDomain.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
