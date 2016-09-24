package servlet;

import util.SQLMan;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 红印 on 2016/9/23.
 */
@WebServlet("/plot_domain")
public class Plot_domain extends MyServlet{

    protected String doMyGet(HttpServletRequest req) {
        String id="";
        if(req.getParameter("ID")!=null)
            id = req.getParameter("ID");
        return plot_domain(id);
    }

    public static String plot_domain(String id){
        String str="{\"type\":\"1\",\"name\":\"";
        String d_name="";
        String m_info="";
        String r_info="";
        String ip_info="";
        String sql_domainIP = null;
        int ip_id=0;
        String str1="";
        String str2="";
        String str3="";
        String str4="";
        String str5="";
        String ipnum="";
        String ww="";
        String[] string=null;
        String d_id="";
        sql_domainIP = "SELECT D_ID,D_Name,D_Registrar,D_Mailbox FROM domain where D_ID=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql_domainIP);
            preS.setString(1, id);
            ResultSet rs = preS.executeQuery();
            if(rs.next()){
                d_id=rs.getString("D_ID");
                d_name=rs.getString("D_Name");
                m_info=rs.getString("D_Mailbox");
                r_info=rs.getString("D_Registrar");
                sql_domainIP = "SELECT IP_ID FROM di where D_ID=? ORDER by DI_Time DESC limit 1";
                preS = SQLMan.getConnection().prepareStatement(sql_domainIP);
                preS.setString(1, id);
                rs=preS.executeQuery();
                if(rs.next())
                ip_id=rs.getInt("IP_ID");
                sql_domainIP = "SELECT IP_Info FROM ip where IP_ID=?";
                preS = SQLMan.getConnection().prepareStatement(sql_domainIP);
                preS.setInt(1, ip_id);
                rs=preS.executeQuery();
                if(rs.next())
                ip_info=rs.getString("IP_Info");
            }
            preS.close();
            ipnum=ip_id+"";
            //提取信息

            str1 = Plot_mailboxDomain.get_mailboxDomain(m_info,d_name).toJson();
            if(str1.equals("[]")){
                str1="";
            }else{
                ww="";
                str1=str1.substring(1,str1.length()-1);
                string = str1.split(",");
                for(int i=0;i<string.length;i++){
                    String str7=string[i].substring(1,string[i].length());
                    if(i==string.length-1) ww="["+ww+"{\"type\":\"1\","+str7+"]";
                    else ww=ww+"{\"type\":\"1\","+str7+",";
                }
                str1=ww;
            }


            str2 = Plot_registrarDomain.get_registrarDomain(r_info,d_name).toJson();
            if(str2.equals("[]")){
                str2="";
            }else{
                ww="";
                str2=str2.substring(1,str2.length()-1);
                string = str2.split(",");
                for(int i=0;i<string.length;i++){
                    String str7=string[i].substring(1,string[i].length());
                    if(i==string.length-1) ww="["+ww+"{\"type\":\"1\","+str7+"]";
                    else ww=ww+"{\"type\":\"1\","+str7+",";
                }
                str2=ww;
            }


            str3 = Plot_IPSample.plot_IPSample(ipnum).toJson();
            if(str3.equals("[]")){
                str3="";
            }else{
                ww="";
                str3=str3.substring(1,str3.length()-1);
                string = str3.split(",");
                for(int i=0;i<string.length;i++){
                    String str7=string[i].substring(1,string[i].length());
                    if(i==string.length-1) ww="["+ww+"{\"type\":\"3\","+str7+"]";
                    else ww=ww+"{\"type\":\"3\","+str7+",";
                }
                str3=ww;
            }

            str4 = Plot_IPDomain.plot_IPDomain(ipnum,d_name).toJson();

            if(str4.equals("[]")){
                str4="";
            }else{
                ww="";
                str4=str4.substring(1,str4.length()-1);
                string = str4.split(",");
                for(int i=0;i<string.length;i++){
                    String str7=string[i].substring(1,string[i].length());
                    if(i==string.length-1) ww="["+ww+"{\"type\":\"1\","+str7+"]";
                    else ww=ww+"{\"type\":\"1\","+str7+",";
                }
                str4=ww;
            }
            str5=Plot_DomainSample.plot_DomainSample(d_id).toJson();
            if(str5.equals("[]")){
                str5="";
            }else{
                ww="";
                str5=str5.substring(1,str5.length()-1);
                string = str5.split(",");
                for(int i=0;i<string.length;i++){
                    String str7=string[i].substring(1,string[i].length());
                    if(i==string.length-1) ww="["+ww+"{\"type\":\"3\","+str7+"]";
                    else ww=ww+"{\"type\":\"3\","+str7+",";
                }
                str5=ww;
            }


            str=str+d_name+"\",\"children\":[";
            if(m_info.equals("")){

            }else{
                str=str+"{\"type\":\"5\",\"name\":\""+m_info+"\"";
                if(str1.equals("")){
                    str=str+"}";
                }else{
                    str=str+",\"children\":"+str1+"}";
                }
            }

            if(r_info.equals("")){

            }else{
                str=str+",{\"type\":\"4\",\"name\":\""+r_info+"\"";
                if(str2.equals("")){
                    str=str+"}";
                }else{
                    str=str+",\"children\":"+str2+"}";
                }
            }

            if(ip_info.equals("")){

            }else{
                str=str+",{\"type\":\"2\",\"name\":\""+ip_info+"\"";
                if(str2.equals("")){
                    str=str+"}";
                }else{
                    str=str+",\"children\":"+str3+"}";
                }
            }

            if(ip_info.equals("")){

            }else{
                str=str+",{\"type\":\"2\",\"name\":\""+ip_info+"\"";
                if(str3.equals("")){
                    if(str4.equals("")){
                        str=str+"}";
                    }else{
                        str=str+",\"children\":"+str4+"}";
                    }

                }else{
                    if(str4.equals("")){
                        str=str+",\"children\":"+str3+"}";
                    }else{
                        str=str+",\"children\":["+str3.substring(0,str3.length()-1)+","+str4.substring(1,str4.length())+"}";
                    }
                }
            }

            if(str5.equals("")){

            }else{
                str=str+","+str5.substring(1,str5.length()-1);
            }
            str+="]}";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }
}
