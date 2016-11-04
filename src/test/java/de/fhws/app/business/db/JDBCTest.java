package de.fhws.app.business.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JDBCTest {

	final static String DB_URL = "jdbc:h2:tcp://localhost/D:/fhws/2016/srv/db/fhws";

	Connection connection;

	/**
	 * <pre>
	 * CREATE TABLE test1 ( 
	 *   id long NOT NULL, 
	 *   data1 varchar2(255), 
	 *   data2 varchar2(255), 
	 *   PRIMARY KEY (id)
	 * );
	 * </pre>
	 * 
	 * @throws SQLException
	 */
	@Before
	public void init() throws SQLException {
		connection = DriverManager.getConnection(DB_URL, "sa", "sa");
		connection.setAutoCommit(false);
	}

	@After
	public void close() throws SQLException {
		connection.close();
	}

	@Test
	public void shouldConnect() throws SQLException {
		Assert.assertTrue(connection.isValid(3));
	}

	/**
	 * <pre>
	 * insert into test1 (id, data1, data2) values(2, 'fhws', 'javaee')
	 * </pre>
	 * 
	 * @throws SQLException
	 */
	@Test
	public void shouldSelect() throws SQLException {
		System.out.println("\nTest Case: shouldSelect");
		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT id, data1, data2 FROM test1");
		while (rs.next()) {
			long id = rs.getLong("id");
			String data1 = rs.getString("data1");
			String data2 = rs.getString("data2");

			// throws SQLException - no casting possible
			// long data2 = rs.getLong("data2");

			System.out.println("id: " + id + "; data1: " + data1 + "; data2: " + data2);
		}
	}

	@Test
	public void shouldSelectWithWhere() throws SQLException {
		System.out.println("\nTest Case: shouldSelectWithWhere");
		String name = "javaee";

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT id, data1, data2 FROM test1 WHERE data2 = '" + name + "'");
		while (rs.next()) {
			long id = rs.getLong("id");
			String data1 = rs.getString("data1");
			String data2 = rs.getString("data2");
			System.out.println("id: " + id + "; data1: " + data1 + "; data2: " + data2);
		}
	}

	// @Test do not execute - will kill your data!
	public void shouldDoASQLInjection() throws SQLException {
		System.out.println("\nTest Case: shouldDoASQLInjection");

		String name = "javaee'; DELETE FROM test1 WHERE data1 != 'xxx";

		Statement stmt = connection.createStatement();

		ResultSet rs = stmt.executeQuery("SELECT id, data1, data2 FROM test1 WHERE data2 = '" + name + "'");
		while (rs.next()) {
			long id = rs.getLong("id");
			String data1 = rs.getString("data1");
			String data2 = rs.getString("data2");
			System.out.println("id: " + id + "; data1: " + data1 + "; data2: " + data2);
		}

		connection.commit();
	}

	@Test
	public void shouldSelectWithPreparedStatement() throws SQLException {
		System.out.println("\nTest Case: shouldSelectWithPreparedStatement");
		String sql = "SELECT id, data1, data2 FROM test1 WHERE data2 = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, "javaee");

		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			long id = rs.getLong("id");
			String data1 = rs.getString("data1");
			String data2 = rs.getString("data2");
			System.out.println("id: " + id + "; data1: " + data1 + "; data2: " + data2);
		}
	}

	@Test
	public void shouldInsertStatement() throws SQLException {
		System.out.println("\nTest Case: shouldInsertStatement");

		String sql = "insert into test1 (id, data1, data2) values(?, ?, ?)";

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, System.currentTimeMillis());
		pstmt.setString(2, "duck");
		pstmt.setString(3, "mouse");

		int numberOfRows = pstmt.executeUpdate();

		connection.commit();
		System.out.println(numberOfRows);
	}

	@Test
	public void shouldDoInOneTransaction() throws SQLException {

		System.out.println("\nTest Case: shouldInsertStatement");

		String sql = "insert into test1 (id, data1, data2) values(?, ?, ?)";

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, System.currentTimeMillis());
		pstmt.setString(2, "mini");
		pstmt.setString(3, "mouse");

		int numberOfRows = pstmt.executeUpdate();

		System.out.println("-insert 1");

		pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, System.currentTimeMillis() + 1);
		pstmt.setString(2, "donald");
		pstmt.setString(3, "duck");

		numberOfRows = pstmt.executeUpdate();

		connection.commit();
		System.out.println(numberOfRows);
	}

	@Test
	public void shouldDoNoCommit() throws SQLException {
		System.out.println("\nTest Case: shouldInsertStatement");

		String sql = "insert into test1 (id, data1, data2) values(?, ?, ?)";

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, System.currentTimeMillis());
		pstmt.setString(2, "daisy");
		pstmt.setString(3, "trick");

		pstmt.executeUpdate();

		try {
		System.out.println(42 / 0);
		} catch(Exception e) {
			connection.rollback();
			return;
		}

		connection.commit();

	}

	@Test
	public void shouldDoRollback() throws SQLException {
		String input2 = "test-" + System.currentTimeMillis();
		System.out.println("\nTest Case: shouldDoRollback");

		String sql = "insert into test1 (id, data1, data2) values(?, ?, ?)";

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, System.currentTimeMillis());
		pstmt.setString(2, "test1");
		pstmt.setString(3, input2);

		pstmt.executeUpdate();

		// data in database

		// select
		sql = "SELECT id, data1, data2 FROM test1 WHERE data2 = ?";
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, input2);

		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count ++;
			long id = rs.getLong("id");
			String data1 = rs.getString("data1");
			String data2 = rs.getString("data2");
			System.out.println("id: " + id + "; data1: " + data1 + "; data2: " + data2);
		}
		Assert.assertEquals(1, count); //data from database

		connection.rollback();
		
		sql = "SELECT id, data1, data2 FROM test1 WHERE data2 = ?";
		pstmt = connection.prepareStatement(sql);
		pstmt.setString(1, input2);

		rs = pstmt.executeQuery();
		count = 0;
		while (rs.next()) {
			count ++;
			long id = rs.getLong("id");
			String data1 = rs.getString("data1");
			String data2 = rs.getString("data2");
			System.out.println("id: " + id + "; data1: " + data1 + "; data2: " + data2);
		}
		Assert.assertEquals(0, count); //data from database


	}

	@Test
	public void shouldDoRollback2() throws SQLException {
		System.out.println("\nTest Case: shouldDoRollback2");

		String sql = "insert into test1 (id, data1, data2) values(?, ?, ?)";

		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setLong(1, System.currentTimeMillis());
		pstmt.setString(2, "test1");
		pstmt.setString(3, "test2");

		pstmt.executeUpdate();
		

		if (10 < 42)
			connection.rollback();
		else
			connection.commit();

	}

}
