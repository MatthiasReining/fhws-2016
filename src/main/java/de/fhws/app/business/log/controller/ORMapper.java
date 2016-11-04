package de.fhws.app.business.log.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

public class ORMapper {

	
	public void save(DataSource ds, Object o) {
		System.out.println("save object " + o);
		
		Map<String, Object> columnMap = new LinkedHashMap<>();
		
		String tableName = o.getClass().getSimpleName();
		
		String sql = "SELECT * FROM " + tableName;
		
		try {
		ResultSet rs = ds.getConnection().createStatement().executeQuery(sql);
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		
		int columnCount = rsmd.getColumnCount();
		for (int i=1; i<=columnCount; i++) {
			String columnName = rsmd.getColumnName(i);
			int columnTypeNumber = rsmd.getColumnType(i);
			String columnType = rsmd.getColumnTypeName(i);
			
			
			columnName = columnName.substring(0,1).toUpperCase() + columnName.substring(1).toLowerCase();
			String methodName = "get" + columnName;
			
			
			Method m = o.getClass().getMethod(methodName, new Class[0]);			
			Object dataValue = m.invoke(o, new Object[0]);			
			columnMap.put(columnName, dataValue);
			
			System.out.println( columnName + "  -  " +  columnType + "(" + columnTypeNumber + ")");
		}
		
		
		
		System.out.println(columnMap);
		
		String sqlInsert = "INSERT INTO " + tableName + "( ";
		for (Entry<String, Object> e: columnMap.entrySet()) {
			sqlInsert += e.getKey() + ", ";	
		}
		sqlInsert = sqlInsert.substring(0, sqlInsert.length()-2);
		sqlInsert += ") VALUES (";
		for (Entry<String, Object> e: columnMap.entrySet()) {
			sqlInsert += "?, ";	
		}
		sqlInsert = sqlInsert.substring(0, sqlInsert.length()-2);
		sqlInsert += ")";
		
		PreparedStatement pstmt =  ds.getConnection().prepareStatement(sqlInsert);
		
		int count = 0;
		for (Entry<String, Object> e: columnMap.entrySet()) {
			count++;
			pstmt.setObject(count, e.getValue());			
		}
		
		pstmt.executeUpdate();
		
		System.out.println(sqlInsert);
		
		
		} catch(SQLException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) {
			e.printStackTrace();
		}
		
		
		
	}
}
