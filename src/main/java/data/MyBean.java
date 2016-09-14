package data;

import com.google.gson.Gson;
import util.MyArrayList;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
public abstract class MyBean {
    /**
     * 使用反射实现的从rs集中映射对象
     *
     * @param rs  resultSet对象集
     * @param cls 要产生的对象的类
     * @return 产生的对象或者是null
     */
    public static MyBean newInstance(ResultSet rs, Class cls) {
        try {
            MyBean bean = (MyBean) cls.newInstance();
            for (Field f : cls.getDeclaredFields()) {
                String fieldType = f.getType().getSimpleName();
                f.setAccessible(true);
                if ("String".equals(fieldType)) {
                    f.set(bean, rs.getString(f.getName()));
                } else if ("int".equals(fieldType)) {
                    f.setInt(bean, rs.getInt(f.getName()));
                } else if ("boolean".equals(fieldType)) {
                    f.set(bean, rs.getBoolean(f.getName()));
                } else if ("double".equals(fieldType)) {
                    f.set(bean, rs.getDouble(f.getName()));
                }
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MyArrayList newInstanceArray(ResultSet rs, Class cls) {
        MyArrayList<MyBean> beans = new MyArrayList<MyBean>();
        try {
            while (rs.next()) {
                beans.add(
                        MyBean.newInstance(rs, cls)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}
