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
@WebServlet("/get_sampleDomain")
public class Get_sampleDomain extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_sampleDomain(id).toJson();
    }
    public static MyArrayList<SampleDomain> get_sampleDomain(String str) {
        MyArrayList<SampleDomain> num = null;
        String sql =null;
        sql="select * from newsd where S_ID=?";

        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();

            num = (MyArrayList<SampleDomain>) MyBean.newInstanceArray(rs, SampleDomain.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
