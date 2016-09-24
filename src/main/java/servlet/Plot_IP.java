package servlet;

import util.SQLMan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 红印 on 2016/9/23.
 */
public class Plot_IP {
    public static String plot_ip(int id){
        String str="{\"type\":\"2\",\"name\":\"";
        String ip_info="";
        String sql_domainIP = null;
        String str1="";
        String str2="";
        String ww="";
        String[] string=null;
        String ip_id="";
        sql_domainIP = "SELECT IP_ID,IP_Info FROM ip where IP_ID=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql_domainIP);
            preS.setInt(1, id);
            ResultSet rs = preS.executeQuery();
            if(rs.next()){
                ip_id=rs.getString("IP_ID");
                ip_info=rs.getString("IP_Info");
            }
            preS.close();
            //提取信息
            str1 = Plot_IPDomain.plot_IPDomain(ip_id,"").toJson();
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

            str2 = Plot_IPSample.plot_IPSample(ip_id).toJson();
            if(str2.equals("[]")){
                str2="";
            }else{
                ww="";
                str2=str2.substring(1,str2.length()-1);
                string = str2.split(",");
                for(int i=0;i<string.length;i++){
                    String str7=string[i].substring(1,string[i].length());
                    if(i==string.length-1) ww="["+ww+"{\"type\":\"3\","+str7+"]";
                    else ww=ww+"{\"type\":\"3\","+str7+",";
                }
                str2=ww;
            }
            if(str1.equals("")){
                if(str2.equals("")){
                    str=str+ip_info+"\"}";
                }else{
                    str=str+ip_info+"\",\"children\":"+str2+"}";
                }
            }else{
                if(str2.equals("")){
                    str=str+ip_info+"\",\"children\":"+str1+"}";
                }else{
                    str=str+ip_info+"\",\"children\":"+str1.substring(0,str1.length()-1)+","+str2.substring(1,str2.length())+"}";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }
}
