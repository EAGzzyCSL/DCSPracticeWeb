package servlet;

import util.SQLMan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 红印 on 2016/9/23.
 */
public class Plot_sample {
    public static String plot_sample(int id){
        String str="{\"type\":\"3\",\"name\":\"";
        String sql_domainIP = null;
        String str1="";
        String str2="";
        String s_info="";
        String ww="";
        String[] string=null;
        String s_id="";
        sql_domainIP = "SELECT S_Sha256 FROM sample where S_ID=?";
        try {
            PreparedStatement preS = SQLMan.getConnection().prepareStatement(
                    sql_domainIP);
            preS.setInt(1, id);
            ResultSet rs = preS.executeQuery();
            if(rs.next()){
                s_info=rs.getString("S_Sha256");
            }
            preS.close();
            //提取信息
            s_id=id+"";
            str1 = Plot_sampleDomain.plot_sampleDomain(s_id).toJson();
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

            str2=Plot_sampleIP.plot_sampleIP(s_id).toJson();
            if(str2.equals("[]")){
                str2="";
            }else{
                ww="";
                str2=str2.substring(1,str2.length()-1);
                string = str2.split(",");
                for(int i=0;i<string.length;i++){
                    String str7=string[i].substring(1,string[i].length());
                    if(i==string.length-1) ww="["+ww+"{\"type\":\"2\","+str7+"]";
                    else ww=ww+"{\"type\":\"2\","+str7+",";
                }
                str2=ww;
            }

            if(str1.equals("")){
                if(str2.equals("")){
                    str=str+s_info+"\"}";
                }else{
                    str=str+s_info+"\",\"children\":"+str2+"}";
                }
            }else{
                if(str2.equals("")){
                    str=str+s_info+"\",\"children\":"+str1+"}";
                }else{
                    str=str+s_info+"\",\"children\":"+str1.substring(0,str1.length()-1)+","+str2.substring(1,str2.length())+"}";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }
}
