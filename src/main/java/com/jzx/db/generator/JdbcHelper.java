package com.jzx.db.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * jdbc帮助类
 * 
 * @author 杨杰
 * @version 2019年5月24日
 * @see JdbcHelper
 * @since
 */
public class JdbcHelper {
	// 获取所有的表
	private final static String getTables = "SELECT table_name, table_type , ENGINE,table_collation,table_comment, create_options FROM information_schema.TABLES WHERE table_schema=\"{0}\"";
	// 获取所有的表描述
	private final static String getTableInfo = "SELECT ordinal_position,column_name,column_type, column_key, extra ,is_nullable, column_default, column_comment,data_type,character_maximum_length FROM information_schema.columns WHERE table_schema=\"{0}\" and table_name=\"{1}\"";

	/**
	 * 初始化连接
	 * 
	 * @param url
	 * @param user
	 * @param pwd
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see
	 */
	public static Connection initConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, user, pwd);
	}

	/**
	 * 关闭连接
	 * 
	 * @param con
	 * @throws SQLException
	 * @see
	 */
	public static void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}

	/**
	 * 获取所有的表
	 * 
	 * @param con
	 * @param dbName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see
	 */
	public static List<Map<String, Object>> getMysqlAllTable(Connection con, String dbName) throws ClassNotFoundException, SQLException {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Statement stmt = con.createStatement();
		String sql = MessageFormat.format(getTables, dbName);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("tableName", rs.getString("table_name"));
			map.put("tableType", rs.getString("table_type"));
			map.put("engine", rs.getString("ENGINE"));
			map.put("tableCollation", rs.getString("table_collation"));
			map.put("tableComment", rs.getString("table_comment"));
			map.put("createOptions", rs.getString("create_options"));
			results.add(map);
		}
		rs.close();
		stmt.close();
		con.close();
		return results;
	}

	/**
	 * 获取表的所有字段
	 * 
	 * @param con
	 * @param dbName
	 * @param tableName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see
	 */
	public static List<Map<String, Object>> getTableInfo(Connection con, String dbName, String tableName) throws ClassNotFoundException, SQLException {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Statement stmt = con.createStatement();
		String sql = MessageFormat.format(getTableInfo, dbName, tableName);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("ordinalPosition", rs.getString("ordinal_position"));
			map.put("columnName", rs.getString("column_name"));
			map.put("columnType", rs.getString("column_type"));
			map.put("columnKey", rs.getString("column_key"));
			map.put("extra", rs.getString("extra"));
			map.put("isNullable", rs.getString("is_nullable"));
			map.put("columnDefault", rs.getString("column_default"));
			map.put("columnComment", rs.getString("column_comment"));
			map.put("dataType", rs.getString("data_type"));
			map.put("characterMaximumLength", rs.getString("character_maximum_length"));
			results.add(map);
		}
		rs.close();
		stmt.close();
		con.close();
		return results;
	}
}
