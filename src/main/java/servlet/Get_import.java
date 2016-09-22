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
@WebServlet("/get_import")
public class Get_import extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_import(id).toJson();
    }
    public static MyArrayList<Import> get_import(String str) {
        MyArrayList<Import> num = null;
        String sql =null;
        sql="SELECT * FROM pe_import where S_ID=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<Import>) MyBean.newInstanceArray(rs, Import.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
