package util;

import servlet.SGetAllMan;


/**
 * Created by eagzzycsl on 9/12/16.
 * 主要用途：
 * 以java程序的方式运行普通的java类，免去在网页上测试jsp和servlet
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // 一个简单的demo查询数据库中的全部男性
        System.out.println(
                SGetAllMan.getAllMan().toJson()
        );
    }
}

