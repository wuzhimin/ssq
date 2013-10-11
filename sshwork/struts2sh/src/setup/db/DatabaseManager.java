/*
 * @(#)DatabaseManager.java 1.00 2005-10-18
 *
 * Copyright 2005 BeanSoft Studio. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package setup.db;

import java.sql.Connection;

/**
 * DatabaseManager is used to make database operations on setup.
 * 
 * Chinese documents:
 * 在安装的时候进行数据库操作.
 * @author BeanSoft
 * @version 1.00 2005-10-18
 */
public interface DatabaseManager {
    /**
     * Return the current database connection.
     * @return java.sql.Connection
     */
    public Connection getConnection();
    
    /**
     * Set the current database connection.
     * @param conn - java.sql.Connection
     */
    public void setConnectioin(Connection conn);
    
    /**
     * Close connection and release all resources.
     */
    public void closeConnection();
    
    /**
     * Check whether the given database is exists.
     * @param database - String, database name
     * @return operation result, false if failed
     */
    public boolean checkDatabaseExist(String database);
    
    /**
     * Create  the given database.
     * @param database - String, database name
     * @return operation result, false if failed
     */    
    public boolean createDatabase(String database);
    
    /**
     * Change to the given database.
     * @param database - String, database name
     * @return operation result, false if failed
     */        
    public boolean changeDatabase(String database);
    
    /**
     * Check whether the JDBC connection can be obtained.
     * @param driver - String, jdbc driver
     * @param url - String, jdbc url
     * @param user - String, jdbc username
     * @param password - String, jdbc password
     * @return Connection - null if faled
     * @throws Exception - if can't create connection
     */
    public Connection checkConnection(String driver, String url, String user, String password) throws Exception;
    
    /**
     * Execute update sql.
     * @param sql - String, sql statement to execute
     *@return operation result, false if failed
     */
    public boolean executeUpdate(String sql);
}
