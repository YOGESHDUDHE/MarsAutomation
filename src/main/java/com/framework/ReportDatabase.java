package com.framework;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;

public class ReportDatabase {
	// JDBC Driver Name & Database URL
	static String dbURL = "jdbc:sqlserver://AZR-EUS2W6853\\MI1130P";
	private Connection conn;
	private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	/**
	 * @Method - connectionDb
	 * @Description - To connect reporting DB
	 * @return Connection
	 * @author aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 04-01-2021
	 */
	public Connection connectDb() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Properties properties = new Properties();
			properties.put("user", TestData.getConfigData("DATABASE_USERNAME"));
			properties.put("password", TestData.getConfigData("DATABASE_PASSWORD"));
			properties.put("database", "Selenium_DB");
			conn = DriverManager.getConnection(dbURL, properties);

			if (conn != null) {
				log.info("Connected to reporting DB");
			}
			return conn;
		} catch (Exception sqlException) {
			log.error(sqlException + "Fail to connect to DB");
			return conn;
		}
	}

	/**
	 * @Method - closeConnection
	 * @Description - To close Connection
	 * @return void
	 * @author aggarkan
	 * @DateCreated - 15-09-2020
	 * @DateModified - 04-01-2021
	 */
	public void closeConnection() {
		try {
			conn.close();
			log.info("Connection to DB Closed");
		} catch (SQLException e) {
			log.error(e + "Fail to close connection");
		}
	}

	/**
	 * @Method - insertIntoReportDB
	 * @Description - To insert execution details to DB
	 * @return void
	 * @param ExtentTest, HashMap
	 * @author aggarkan
	 * @throws Exception
	 * @DateCreated - 15-09-2020
	 * @DateModified - 08-11-2021
	 */
	public void insertIntoReportDB(ExtentTest testlog, HashMap<Object, Object> testCaseData) throws Exception {
		try {
			if (TestData.getConfigData("SAVE_LOGS").equalsIgnoreCase("true")) {
				ReportDatabase objReportDB = new ReportDatabase();
				PreparedStatement testResults = null;
				conn = connectDb();
				testResults = conn.prepareStatement("insert into ExecutionDetailsSelenium"
						+ "(testCaseName, result, startTime, endTime, duration, manualExecutionTime, applicationName, "
						+ "segmentName, geographyName, projectID, environment) values (?,?,?,?,?,?,?,?,?,?,?)");
				testResults.setString(1, testCaseData.get("testCaseName").toString());
				testResults.setString(2, testlog.getModel().getStatus().toString());
				// java.sql.Date date=new
				// java.sql.Date(testlog.getModel().getStartTime().getTime());

				testResults.setTimestamp(3, new java.sql.Timestamp(testlog.getModel().getStartTime().getTime()));
				testResults.setTimestamp(4, new java.sql.Timestamp(testlog.getModel().getEndTime().getTime()));
				Long duration = testlog.getModel().getRunDurationMillis();
				testResults.setLong(5, duration / 1000);
				testResults.setInt(6, (int) testCaseData.get("manualExecutionTime"));
				testResults.setString(7, testCaseData.get("applicationName").toString());
				testResults.setString(8, testCaseData.get("segment").toString());
				testResults.setString(9, testCaseData.get("geography").toString());
				testResults.setString(10, testCaseData.get("projectID").toString());
				testResults.setString(11, testCaseData.get("environment").toString());

				testResults.execute();
				objReportDB.closeConnection();
				testResults.close();
			} else {
				System.out.println("Execution logs not saved");
			}
		} catch (SQLException e) {
			log.warn("Issuess with Insert Query");
		} catch (NullPointerException e) {
			log.warn("Issuess with DB Connection");
		} catch (Exception e) {
			log.warn("Issuess reading config for DB");
		}
	}
}
