/*
 * @(#)DatabaseManagerImplMysql.java 1.00 2005-10-18
 *
 * Copyright 2005 BeanSoft Studio. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package setup.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import util.sql.DatabaseUtil;

/**
 * DatabaseManagerImplMysql is a Mysql database implementation of DatabaseManager.  
 *
 * Chinese documents:
 * DatabaseManager 的 Mysql 实现版本.
 * @author BeanSoft
 * @version 1.00 2005-10-18
 */
public class DatabaseManagerImplMysql implements DatabaseManager {
    /** Database connection */
    private Connection conn = null;
    
    /** Cached sql statement object */
    private Statement stmt = null;
    
    /** Database operation help class */ 
    private DatabaseUtil  dbUtil = new DatabaseUtil();
    /**
     *
     */
    public DatabaseManagerImplMysql() {
    }

    public Connection getConnection() {
        return conn;
    }

    public void setConnectioin(Connection conn) {
        this.conn = conn;
        dbUtil.setConnection(getConnection());
        stmt = dbUtil.getStatement();
    }

    public boolean checkDatabaseExist(String database) {
        ResultSet rs = dbUtil.executeQuery(
        "SHOW DATABASES LIKE '" + database + "';");

        try {
            if(rs != null && rs.next()) {
                String dbname = rs.getString(1);

                if(dbname.equalsIgnoreCase(database)) {
                    return true;
                }
            }
        } catch (SQLException e) {
        } finally {
            dbUtil.closeJDBCResource(rs);
        }
        return false;
    }

    public boolean createDatabase(String database) {
        int rows = dbUtil.executeUpdate("CREATE DATABASE " + database + ";");
        return rows > 0;
    }

    public boolean changeDatabase(String database) {
        int rows = dbUtil.executeUpdate("USE " + database + ";");
        return true;
    }

    public Connection checkConnection(String driver, String url, String user, String password) throws Exception {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw e;
        }

        return DriverManager.getConnection(url, user, password);
    }

    public boolean executeUpdate(String sql) {
        int rows = 0;
        try {
            rows = stmt.executeUpdate(sql);
        } catch (SQLException e) {
        }
        
        return rows >= 0;
    }

    public void closeConnection() {
        dbUtil.closeJDBCResource(stmt);
        dbUtil.close();
    }

    public static void main(String[] args) {
        DatabaseManager manager  = new DatabaseManagerImplMysql();
        System.out.println(manager.checkDatabaseExist("code"));

        manager.closeConnection();
    }

}