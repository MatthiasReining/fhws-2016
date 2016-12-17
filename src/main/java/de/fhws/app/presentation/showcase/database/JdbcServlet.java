package de.fhws.app.presentation.showcase.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("jdbc")
public class JdbcServlet extends HttpServlet {

	@Resource(lookup = "java:jboss/datasources/FhwsDS")
	DataSource datasource;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Connection connection = datasource.getConnection();
			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, data1, data2 FROM test1");
			while (rs.next()) {
				long id = rs.getLong("id");
				String data1 = rs.getString("data1");
				String data2 = rs.getString("data2");
				resp.getWriter().println("id: " + id + "; data1: " + data1 + "; data2: " + data2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
