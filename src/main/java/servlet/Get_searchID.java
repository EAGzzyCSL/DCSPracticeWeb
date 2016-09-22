package servlet;

/**
 * Created by 红印 on 2016/9/22.
 */
import data.ID;
import data.MyBean;
import data.Person;
import data.Sample;
import util.MyArrayList;
import util.SQLMan;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
@WebServlet("/get_searchID")
public class Get_searchID extends MyServlet{
    @Override
    protected String doMyGet(HttpServletRequest req) {
        String ip="";
        String domain="";
        String md5="";
        String sha1="";
        String sha256 = "";
        if(req.getParameter("ip")!=null){
            ip = req.getParameter("ip");
            return get_searchID(ip,1).toJson();
        }
        if(req.getParameter("domain")!=null){
            domain = req.getParameter("domain");
            return get_searchID(domain,2).toJson();
        }
        if(req.getParameter("md5")!=null){
            md5 = req.getParameter("md5");
            return get_searchID(md5,3).toJson();
        }
        if(req.getParameter("sha1")!=null){
            sha1 = req.getParameter("sha1");
            return get_searchID(sha1,4).toJson();
        }
        if(req.getParameter("sha256")!=null){
            sha256 = req.getParameter("sha256");
            return get_searchID(sha256,5).toJson();
        }
        return "{msg:请输入查询信息}";
    }
    public static MyArrayList<ID> get_searchID(String str,int mark) {
        MyArrayList<ID> id = null;
        String sql_getID =null;
        if(1==mark)
            sql_getID="SELECT IP_ID as ID FROM ip WHERE IP_Info=?";
        if(2==mark)
            sql_getID="SELECT D_ID as ID FROM domain WHERE D_Name=?";
        if(3==mark)
            sql_getID="SELECT S_ID as ID FROM sample WHERE S_MD5=?";
        if(4==mark)
            sql_getID="SELECT S_ID as ID FROM sample WHERE S_Sha1=?";
        if(5==mark)
            sql_getID="SELECT S_ID as ID FROM sample WHERE S_Sha256=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql_getID);
            preS.setString(1, str);
            ResultSet rs = preS.executeQuery();
            id = (MyArrayList<ID>) MyBean.newInstanceArray(rs, ID.class);
            preS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

}
