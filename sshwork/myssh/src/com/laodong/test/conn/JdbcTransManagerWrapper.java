package com.laodong.test.conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JdbcTransManagerWrapper implements ITransManagerWrapper {

	private Log log = log = LogFactory.getLog(this.getClass());

	private static ThreadLocal _refCount = new ThreadLocal();

	private static ThreadLocal con = new ThreadLocal();

	private static ThreadLocal isRollback = new ThreadLocal();

	private static DataSource ds = null;

	public synchronized static Connection getCon() {
		try {
			if (ds == null) {
				Context initCtx=new InitialContext();
				ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/laodong");
			}
			if (con.get() == null) {
				Connection conn = ds.getConnection();
				con.set(conn);
				isRollback.set(new Boolean(false));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (Connection) con.get();
	}

	public synchronized void beginTransaction() {
		if (log.isInfoEnabled()) {
			log.info("before jdbc beginTransaction(): i = " + getRefCount());
		}

		if (getRefCount() == 0) {
			actualBeginTransaction();
		}
		setRefCount(getRefCount() + 1);
		if (log.isInfoEnabled()) {
			log.info("after jdbc beginTransaction(): i = " + getRefCount());
		}
	}

	public synchronized void commitTransaction() {
		if (log.isInfoEnabled()) {
			log.info("before jdbc Transaction commit() : i = " + getRefCount());
		}

		setRefCount(getRefCount() - 1);

		if (log.isInfoEnabled()) {
			log.info("after jdbc Transaction commit() : i = " + getRefCount());
		}
		if (getRefCount() == 0 && !getIsRollback()) {
			actualCommit();
		} else if (getRefCount() == 0 && getIsRollback()) {
			rollback();
		}
	}

	public void rollback() {
		actualRollback();
	}

	public synchronized void releaseResource() {
		try {
			if (getCon() != null) {
				getCon().close();
			}
			con.set(null);
			isRollback.set(new Boolean(false));
		} catch (SQLException e) {
			log.error("release connection occur error");
			e.printStackTrace();
		}
	}

	private void actualBeginTransaction() {

		try {
			getCon().setAutoCommit(false);
		} catch (SQLException e) {
			log.error("commit transaction  occur error");
			e.printStackTrace();
		}
		if (log.isInfoEnabled()) {
			log.info("jdbc Transaction Begined");
		}
	}

	private void actualCommit() {
		if (log.isInfoEnabled()) {
			log.debug("Executing jdbc Transaction commit()");
		}
		try {
			getCon().commit();
		} catch (Exception e) {
			log.error("Error occurs while jdbc commit transaction: ", e);

		}

		if (log.isInfoEnabled()) {
			log.debug("ibatis Transaction Commited");
		}
	}

	private void actualRollback() {
		if (log.isInfoEnabled()) {
			log.info("Executing jdbc Transaction rollback()");
		}
		try {
			getCon().rollback();
			setIsRollback(true);
		} catch (Exception e) {
			log.error("Rollback transaction  occur error");
		}
		if (log.isInfoEnabled()) {
			log.info("ibatis Transaction Rolled Back");
		}
	}

	private int getRefCount() {
		if (_refCount.get() == null) {
			_refCount.set(new Integer(0));
		}
		return ((Integer) _refCount.get()).intValue();
	}

	private void setRefCount(int count) {
		_refCount.set(new Integer(count));
	}

	public static boolean getIsRollback() {
		return ((Boolean) isRollback.get()).booleanValue();
	}

	public static void setIsRollback(boolean fOrT) {
		isRollback.set(Boolean.valueOf(fOrT));
	}

}
