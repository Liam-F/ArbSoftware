package clarke.sw.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import clarke.sw.calculateArbs.ArbData;
import clarke.sw.scraper.Sports;

public class Database {

	// Database connection details.
	private LinkedList<ArbData> listOfArbs;
	private Connection conn;
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String CONNECTION_URL = "jdbc:mysql://160.153.162.158:3306/ArbitrageTradingSite";
	private final String DB_USER = "shaneclarke";
	private final String DB_PASSWORD = "1Thunder1";

	public Database(LinkedList<ArbData> listOfArbs) throws Exception {
		this.listOfArbs = listOfArbs;
		connect();
		deleteFromDb();
		insertToDb();
		disconnect();
	}

	// Connect to the database.
	public void connect() {
		if (conn != null)
			return;

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("Db driver not found.");
		}

		try {
			conn = (Connection) DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("error connecting to db");
		}

	}
	
	// Disconnect fromt he database.
	public void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("error closing connection");
			}
		}
	}

	// Insert to the database using a prepared statement so sql injections cant happen.
	public void insertToDb() throws Exception {
		System.out.println("inserting");

		String insertSql = "insert into ArbitrageTrades (profit, arbPercentage, playerOne, playerOneBet, playerOneSite, playerOneOdds, "
				+ "playerTwo, playerTwoBet, playerTwoSite, playerTwoOdds, sport) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement insertStmt = conn.prepareStatement(insertSql);

		for (ArbData data : listOfArbs) {

			double profit = data.getProfit();
			double arbPercentage = data.getArbPercentage();
			String playerOne = data.getPlayer1();
			double playerOneBet = data.getPlayer1BetAmount();
			String playerOneSite = data.getPlayer1Website();
			double playerOneOdds = data.getPlayer1Odds();
			String playerTwo = data.getPlayer2();
			double playerTwoBet = data.getPlayer2BetAmount();
			String playerTwoSite = data.getPlayer2Website();
			double playerTwoOdds = data.getPlayer2Odds();
			Sports sport = data.getSport();

			int col = 1;
			insertStmt.setDouble(col++, profit);
			insertStmt.setDouble(col++, arbPercentage);
			insertStmt.setString(col++, playerOne);
			insertStmt.setDouble(col++, playerOneBet);
			insertStmt.setString(col++, playerOneSite);
			insertStmt.setDouble(col++, playerOneOdds);
			insertStmt.setString(col++, playerTwo);
			insertStmt.setDouble(col++, playerTwoBet);
			insertStmt.setString(col++, playerTwoSite);
			insertStmt.setDouble(col++, playerTwoOdds);
			insertStmt.setString(col++, sport.name());

			insertStmt.executeUpdate();
		}

		insertStmt.close();
	}
	
	// Delete data from the database.
	public void deleteFromDb() throws SQLException{
		System.out.println("Deleting");
		
		String deleteSql = "delete from ArbitrageTrades";
		
		PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
		
		deleteStmt.executeUpdate();
		
		deleteStmt.close();
	}

	
	// Test the databse.
//	public static void main(String[] args) {
//
//		LinkedList<ArbData> ad = new LinkedList<>();
//
//		ad.add(new ArbData(10, 90, "abc", 50, "pp", 2.1, "def", 50, "wh", 2.1, Sports.TENNIS));
//
//		Database db = new Database(ad);
//
//		System.out.println("db test");
//
//		db.connect();
//
//		try {
//			db.deleteFromDb();
//			db.insertToDb();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		db.disconnect();
//	}
}
