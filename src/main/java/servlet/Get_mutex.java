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
@WebServlet("/get_mutex")
public class Get_mutex extends MyServlet{
    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return get_mutex(id).toJson();
    }
    public static MyArrayList<Mutex> get_mutex(String str) {
        MyArrayList<Mutex> num = null;
        String sql =null;
        sql="SELECT * FROM mutex where S_ID=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            num = (MyArrayList<Mutex>) MyBean.newInstanceArray(rs, Mutex.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
