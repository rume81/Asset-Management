package com.ey.application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	// --- Globals
	Statement stmt;
	Connection conn;

	public DBManager() throws Exception {
		String sDriverName = "org.sqlite.JDBC";
		// now we set up a set of fairly basic string variables to use in the
		// body of the code proper
		String workingDir = System.getProperty("user.dir");
		//System.out.println("=======================  "+workingDir);
		String sTempDb = workingDir+"\\DB\\EY_db.db";
		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + sTempDb;
		// which will produce a legitimate Url for SqlLite JDBC :
		// jdbc:sqlite:ey_asset.db
		int iTimeout = 30;
		// String sMakeTable = "CREATE TABLE dummy (id numeric, response text)";
		// String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the
		// database')";
		// String sMakeSelect = "SELECT response from dummy";

		try {
			// register the driver
			Class.forName(sDriverName);
			// create a database connection
			conn = DriverManager.getConnection(sDbUrl);
			stmt = conn.createStatement();
			/*
			 * try { stmt.setQueryTimeout(iTimeout); stmt.executeUpdate(
			 * sMakeTable ); stmt.executeUpdate( sMakeInsert ); ResultSet rs =
			 * stmt.executeQuery(sMakeSelect); try { while(rs.next()) { String
			 * sResult = rs.getString("response"); System.out.println(sResult);
			 * } } finally { try { rs.close(); } catch (Exception ignore) {} } }
			 * finally { try { stmt.close(); } catch (Exception ignore) {} }
			 */
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Driver Not Found: " + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("Error Connecting database: " + sqle.toString());
		}
		/*
		 * finally { try { conn.close(); } catch (Exception ignore) {} }
		 */
	}

	// ***********************
	// Public Methods
	// ***********************

	/*
	 ** Returns ResultSet Object obtianed from executing query
	 **
	 ** @param strSql Query String to execute
	 ** 
	 * @return ResultSet
	 */
	public ResultSet getRecord(String strSql) throws SQLException {
		return stmt.executeQuery(strSql);
	}

	/*
	 ** Execute modification query and returns true if success, false if failure
	 **
	 ** @param strSql Query String to upadate/insert/delete
	 ** 
	 * @return boolean, state of success
	 */
	public boolean doQuery(String strSql) throws SQLException {
		return stmt.executeUpdate(strSql) > 0;
	}

	/*
	 ** close Sataement & Connection cleanup code
	 **
	 */
	public void close() throws SQLException {
		stmt.close();
		conn.close();
		conn = null;
		stmt = null;
		
	}
}
