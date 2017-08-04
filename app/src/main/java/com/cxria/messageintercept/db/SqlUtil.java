package com.cxria.messageintercept.db;

import android.util.Log;

import com.cxria.messageintercept.LogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by whr on 17-7-4.
 */

public class SqlUtil {

    private static String url;
    private static String userName;
    private static String passWord;
    private ExecutorService mSqlTread = Executors.newSingleThreadExecutor();

    private static SqlUtil mSqlUtil = null;

    private SqlUtil() {
    }

//    For Example:
//    Class.forName("com.mysql.jdbc.Driver");
//    String url = "jdbc:mysql://192.168.1.21:3306/grassroot";
//    Connection conn = DriverManager.getConnection(url, "duanyan", "chengxun");

    public static SqlUtil getInstance(String url, String userName, String passWord) {
        if (mSqlUtil == null) {
            mSqlUtil = new SqlUtil();
            SqlUtil.url = url;
            SqlUtil.userName = userName;
            SqlUtil.passWord = passWord;
        }
        return mSqlUtil;
    }

    public void query(String tableName) {
        //注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mSqlTread.execute(() -> {
            try {
                Connection conn = DriverManager.getConnection(url, userName, passWord);
                Statement stmt = conn.createStatement();
                String sql = "select * from " + tableName;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Log.i("TAG", "id-->" + rs.getString(1) + " number-->" + rs.getString(2) + " code-->" + rs.getString(3) + " expiry-->" + rs.getString(4));
                }
                rs.close();
                stmt.close();
                conn.close();
                Log.i("TAG", "success to connect!");
            } catch (SQLException e) {
                Log.i("TAG", "fail to connect!" + "  " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void insert(String tableName, String number, String code, String expiry, String content) {
        //注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        mSqlTread.execute(() -> {
            try {
                Connection conn = DriverManager.getConnection(url, userName, passWord);
                Statement stmt = conn.createStatement();
                String sql = "insert into " + tableName + " (number,code,expiry,content) values " +
                        "(" + number + "," + code + ",\'" + expiry + "\',\'" + content + "\')";
                LogUtil.setLog(sql);
                stmt.execute(sql);
                stmt.close();
                conn.close();
                Log.i("TAG", "success to insert!");
            } catch (SQLException e) {
                Log.i("TAG", "fail to connect!" + "  " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

}
